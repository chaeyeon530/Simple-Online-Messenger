package Project;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

class Client extends Thread
{
    Server server;
    Socket socket;

    Vector<Client> auser;
    Vector<Client> wuser;
    Vector<Room> room;

    DB db = new DB();

    OutputStream out;
    DataOutputStream d_out;
    InputStream in;
    DataInputStream d_in;

    String msg;
    String nickname;

    Room myRoom;

    /* 각 메시지를 구분하기 위한 태그 */
    final String viewTag = "VIEW";		//회원정보조회
    final String changeTag = "CHANGE";	//회원정보변경
    final String croomTag = "CROOM";	//방생성
    final String vroomTag = "VROOM";	//방목록
    final String uroomTag = "UROOM";	//방유저
    final String eroomTag = "EROOM";	//방입장
    final String cuserTag = "CUSER";	//접속유저
    final String pexitTag = "PEXIT";	//프로그램종료
    final String rexitTag = "REXIT";	//방퇴장

    Client(Socket _s, Server _ss)
    {
        this.socket = _s;
        this.server = _ss;

        auser = server.alluser;
        wuser = server.waituser;
        room = server.room;
    }

    public void run()
    {
        try
        {
            System.out.println("[Server] 클라이언트 접속 > " + this.socket.toString());

            out = this.socket.getOutputStream();
            d_out = new DataOutputStream(out);
            in = this.socket.getInputStream();
            d_in = new DataInputStream(in);
            String[] m = new String[3];
            m[0] = "id";
            m[1] = "password";
            m[2] = "nickname";

            while(true)
            {

                String mm = db.loginCheck(m[1], m[2]);

                if(!mm.equals("null"))
                    {
                        nickname = mm;

                        auser.add(this);
                        wuser.add(this);

                        sendWait(connectedUser());
                        if(room.size() > 0)
                        {
                            sendWait(roomInfo());
                        }
                    }


                /* 방 생성 */
                else if(m[0].equals(croomTag))
                {
                    myRoom = new Room();
                    myRoom.title = m[1];
                    myRoom.count++;

                    room.add(myRoom);

                    myRoom.ccu.add(this);
                    wuser.remove(this);

                    d_out.writeUTF(croomTag + "//OKAY");
                    System.out.println("[Server] "+ nickname + " : 방 '" + m[1] + "' 생성");

                    sendWait(roomInfo());
                    sendRoom(roomUser());
                }

                /* 방 입장 */
                else if(m[0].equals(eroomTag))
                {
                    for(int i=0; i<room.size(); i++)
                    {
                        Room r = room.get(i);
                        if(r.title.equals(m[1]))
                        {
                            if(r.count < 2)
                            {
                                myRoom = room.get(i);
                                myRoom.count++;

                                wuser.remove(this);
                                myRoom.ccu.add(this);

                                sendWait(roomInfo());
                                sendRoom(roomUser());

                                d_out.writeUTF(eroomTag + "//OKAY");
                                System.out.println("[Server] " + nickname + " : 방 '" + m[1] + "' 입장");
                            }

                            else
                            {
                                d_out.writeUTF(eroomTag + "//FAIL");
                                System.out.println("[Server] 인원 초과. 입장 불가능");
                            }
                        }

                        else {
                            d_out.writeUTF(eroomTag + "//FAIL");
                            System.out.println("[Server] " + nickname + " : 방 '" + m[1] + "' 입장 오류");
                        }
                    }
                }


                /* 프로그램 종료 */
                else if(m[0].equals(pexitTag))
                {
                    auser.remove(this);
                    wuser.remove(this);

                    sendWait(connectedUser());
                }

                /* 방 퇴장 */
                else if(m[0].equals(rexitTag))
                {
                    myRoom.ccu.remove(this);
                    myRoom.count--;
                    wuser.add(this);

                    System.out.println("[Server] " + nickname + " : 방 '" + myRoom.title + "' 퇴장");

                    if(myRoom.count==0) {
                        room.remove(myRoom);
                    }

                    if(room.size() != 0) {
                        sendRoom(roomUser());

                    }

                    sendWait(roomInfo());
                    sendWait(connectedUser());
                }
            }
        } catch(IOException e) {
            System.out.println("[Server] 입출력 오류 > " + e.toString());
        }
    }

    /* 현재 존재하는 방의 목록을 조회하는 메소드 */
    String roomInfo() {
        String msg = vroomTag + "//";

        for(int i=0; i<room.size(); i++) {
            msg = msg + room.get(i).title + " : " + room.get(i).count + "@";
        }
        return msg;
    }

    /* 클라이언트가 입장한 방의 인원을 조회하는 메소드 */
    String roomUser()
    {
        String msg = uroomTag + "//";

        for(int i=0; i<myRoom.ccu.size(); i++) {
            msg = msg + myRoom.ccu.get(i).nickname + "@";
        }
        return msg;
    }

    /* 접속한 모든 회원 목록을 조회하는 메소드 */
    String connectedUser()
    {
        String msg = cuserTag + "//";

        for(int i=0; i<auser.size(); i++) {
            msg = msg + auser.get(i).nickname + "@";
        }
        return msg;
    }

    /* 대기실에 있는 모든 회원에게 메시지 전송하는 메소드 */
    void sendWait(String m)
    {
        for(int i=0; i<wuser.size(); i++) {
            try {
                wuser.get(i).d_out.writeUTF(m);
            } catch(IOException e) {
                wuser.remove(i--);
            }
        }
    }

    /* 방에 입장한 모든 회원에게 메시지 전송하는 메소드 */
    void sendRoom(String m)
    {
        for(int i=0; i<myRoom.ccu.size(); i++) {
            try {
                myRoom.ccu.get(i).d_out.writeUTF(m);
            } catch(IOException e) {
                myRoom.ccu.remove(i--);
            }
        }
    }


}


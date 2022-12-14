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

    /* �� �޽����� �����ϱ� ���� �±� */
    final String viewTag = "VIEW";		//ȸ��������ȸ
    final String changeTag = "CHANGE";	//ȸ����������
    final String croomTag = "CROOM";	//�����
    final String vroomTag = "VROOM";	//����
    final String uroomTag = "UROOM";	//������
    final String eroomTag = "EROOM";	//������
    final String cuserTag = "CUSER";	//��������
    final String pexitTag = "PEXIT";	//���α׷�����
    final String rexitTag = "REXIT";	//������

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
            System.out.println("[Server] Ŭ���̾�Ʈ ���� > " + this.socket.toString());

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


                /* �� ���� */
                else if(m[0].equals(croomTag))
                {
                    myRoom = new Room();
                    myRoom.title = m[1];
                    myRoom.count++;

                    room.add(myRoom);

                    myRoom.ccu.add(this);
                    wuser.remove(this);

                    d_out.writeUTF(croomTag + "//OKAY");
                    System.out.println("[Server] "+ nickname + " : �� '" + m[1] + "' ����");

                    sendWait(roomInfo());
                    sendRoom(roomUser());
                }

                /* �� ���� */
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
                                System.out.println("[Server] " + nickname + " : �� '" + m[1] + "' ����");
                            }

                            else
                            {
                                d_out.writeUTF(eroomTag + "//FAIL");
                                System.out.println("[Server] �ο� �ʰ�. ���� �Ұ���");
                            }
                        }

                        else {
                            d_out.writeUTF(eroomTag + "//FAIL");
                            System.out.println("[Server] " + nickname + " : �� '" + m[1] + "' ���� ����");
                        }
                    }
                }


                /* ���α׷� ���� */
                else if(m[0].equals(pexitTag))
                {
                    auser.remove(this);
                    wuser.remove(this);

                    sendWait(connectedUser());
                }

                /* �� ���� */
                else if(m[0].equals(rexitTag))
                {
                    myRoom.ccu.remove(this);
                    myRoom.count--;
                    wuser.add(this);

                    System.out.println("[Server] " + nickname + " : �� '" + myRoom.title + "' ����");

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
            System.out.println("[Server] ����� ���� > " + e.toString());
        }
    }

    /* ���� �����ϴ� ���� ����� ��ȸ�ϴ� �޼ҵ� */
    String roomInfo() {
        String msg = vroomTag + "//";

        for(int i=0; i<room.size(); i++) {
            msg = msg + room.get(i).title + " : " + room.get(i).count + "@";
        }
        return msg;
    }

    /* Ŭ���̾�Ʈ�� ������ ���� �ο��� ��ȸ�ϴ� �޼ҵ� */
    String roomUser()
    {
        String msg = uroomTag + "//";

        for(int i=0; i<myRoom.ccu.size(); i++) {
            msg = msg + myRoom.ccu.get(i).nickname + "@";
        }
        return msg;
    }

    /* ������ ��� ȸ�� ����� ��ȸ�ϴ� �޼ҵ� */
    String connectedUser()
    {
        String msg = cuserTag + "//";

        for(int i=0; i<auser.size(); i++) {
            msg = msg + auser.get(i).nickname + "@";
        }
        return msg;
    }

    /* ���ǿ� �ִ� ��� ȸ������ �޽��� �����ϴ� �޼ҵ� */
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

    /* �濡 ������ ��� ȸ������ �޽��� �����ϴ� �޼ҵ� */
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


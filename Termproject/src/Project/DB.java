package Project;

import java.sql.*;

public class DB {

    Connection conn = null;
    Statement stmt = null;

    //sql connection
    DB() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost/kakao ?severTimeZone = UTC&use SSL=false";
            String user = "root", passwd= "1234";

            conn = DriverManager.getConnection(url, user, passwd);
            System.out.println(conn);

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    String loginCheck(String _i, String _p)
    {
        String nickname = "null";
        String id = _i;
        String pw = _p;

        try
        {

            String checkingStr = "SELECT password, nickname FROM User WHERE id='" + id + "'";
            ResultSet result = stmt.executeQuery(checkingStr);

            int count = 0;
            while(result.next())
            {
                if(pw.equals(result.getString("password")))
                {
                    nickname = result.getString("nickname");
                }

                else
                {
                    nickname = "null";
                }
                count++;
            }
        } catch(Exception e)
        {
            nickname = "null";
        }

        return nickname;

    }
    //login
    public boolean checkUser(String id, String pw) {
        try {
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt2
                    .executeQuery("select user_id from User where user_id = '" + id + "' and password = '" + pw + "';");
            if (rs.next() == false) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //id 중복 처리
    public boolean checkUser(String id) {
        try {
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt2.executeQuery("select user_id from User where user_id = '" + id + "';");
            if (rs.next() == false) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    //회원가입
    public void addUser(String id, String pw, String email, String name, String nickname, String createdate, String birthday, String phone, String gender) {

        try {

            PreparedStatement pstmt= conn.prepareStatement("insert into User values (?,?,?,?,?,?,?,?,?);");

            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, email);
            pstmt.setString(4, name);
            pstmt.setString(5, nickname);
            pstmt.setString(6, createdate);
            pstmt.setString(7, birthday);
            pstmt.setString(8, phone);
            pstmt.setString(9, gender);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //user information 받아오기
    public String userInfo(String id) {

        String comment="";
        String info="";
        String ip= "",  port="", con="", time_last_login="",time_last_logout="", number_of_login="";


        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select user_ip, user_port, connection, time_last_login, number_of_login,comment,  time_last_logout from information where user_id =  '" + id + "';");

            ip = rset.getString(1);
            port = rset.getString(2);
            con = rset.getString(3);
            time_last_login = rset.getString(4);
            number_of_login = rset.getString(5);
            comment = rset.getString(6);
            time_last_logout = rset.getString(7);


            info = info+ip+"/" + port+"/" + con+"/"+time_last_login+"/"+number_of_login+"/"+time_last_logout+"/"+comment;


            rset.close();
            stmt.close();

        }catch(SQLException e) {
            e.printStackTrace();
        }


        return info;
    }

    //comment update
    public void updateComment(String id, String comment) {

        try {

            String sql1 = String.format("UPDATE information SET comment = %s where user_id = %s", comment, id);

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql1);

            rset.next();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //time of last logout update
    public void updateTimeLastLogout(String id, String Now ) {

        try {

            String sql2 = String.format("UPDATE information SET time_last_logout = %s where user_id = %s ", Now, id);

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql2);

            rset.next();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public String showID (String findName, String findEmail) {

        String ID ="";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select user_id from User where name= '" + findName + "' and email = '" + findEmail + "';");
            if (rs.next() == false) {
                return "존재하지 않는 사용자 정보입니다.";}
            else {
                ID=ID+rs;
                return ID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "존재하지 않는 사용자 정보입니다.";

    }


    public String showPW (String findID, String findemail) {

        String PW ="";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select password from User where name= '" + findemail + "' and user_id = '" + findID + "';");
            if (rs.next() == false) {
                return "존재하지 않는 사용자 정보입니다.";}
            else {
                PW=PW+rs;
                return PW;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "존재하지 않는 사용자 정보입니다.";

    }


    public static void main(String[] args) {
        DB a = new DB();
    }


}

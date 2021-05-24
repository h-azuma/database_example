package sql;

import dao.UserDAO;
import value.UserValue;
import encryption.PasswordEncryptor;
import encryption.SaltMaker;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        List<UserValue> userList = userDAO.findAll();

        System.out.print("id\t");
        System.out.print("name\t");
        System.out.println("password");

        String safetyPassword = PasswordEncryptor.encryptPassword("password", SaltMaker.makeSalt("UID000001"));
        System.out.println(safetyPassword);
    }
}

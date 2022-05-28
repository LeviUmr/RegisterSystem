package VIEW;

import DAO.ConnectionDAO;

public class Program {
    public static void main(String[] args) throws Exception {

        ConnectionDAO db = new ConnectionDAO();
        db.getConnection();
        new MainJFrame();

    }
}

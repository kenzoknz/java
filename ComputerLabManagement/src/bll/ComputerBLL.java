package bll;

import dal.ComputerDAO;
import model.Computer;
import java.util.List;

public class ComputerBLL {
    private ComputerDAO computerDAO = new ComputerDAO();

    public List<Computer> getComputersByLab(int labId) {
        return computerDAO.getComputersByLab(labId);
    }

    public boolean addComputer(Computer computer) {
        return computerDAO.addComputer(computer);
    }

    public boolean updateComputer(Computer computer) {
        return computerDAO.updateComputer(computer);
    }

    public boolean deleteComputer(int id) {
        return computerDAO.deleteComputer(id);
    }

    public List<Computer> searchComputers(String keyword, Integer labId) {
        return computerDAO.searchComputers(keyword, labId);
    }
}
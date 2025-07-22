package bll;

import dal.ComputerLabDAO;
import dal.ComputerDAO;
import model.Computer;
import model.ComputerLab;
import java.util.List;

import javax.swing.JOptionPane;

public class ComputerLabBLL {
    private ComputerLabDAO labDAO = new ComputerLabDAO();

    public List<ComputerLab> getAllLabs() {
        return labDAO.getAllLabs();
    }

    public boolean addLab(ComputerLab lab) {
        return labDAO.addLab(lab);
    }

    public boolean updateLab(ComputerLab lab) {
        return labDAO.updateLab(lab);
    }
    public boolean deleteLab(int id) {
        ComputerBLL computerBLL = new ComputerBLL();
        List<Computer> computers = computerBLL.getComputersByLab(id);
        
        if (!computers.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Phòng này vẫn còn " + computers.size() + " máy tính. Bạn có muốn xóa tất cả máy tính và phòng không?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return false;
            }
        }
        
        return labDAO.deleteLab(id);
    }

    public List<ComputerLab> searchLabs(String keyword) {
        return labDAO.searchLabs(keyword);
    }
}
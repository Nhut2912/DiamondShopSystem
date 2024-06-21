package com.example.server.Service.Warranty;

import com.example.server.Model.WarrantyDTO;
import com.example.server.Pojo.Warranty;

import java.util.List;

public interface IWarrantyService {
    public boolean createWarranty(WarrantyDTO warrantyDTO);
    public boolean updateWarranty(WarrantyDTO warrantyDTO);
    public boolean deleteWarranty(WarrantyDTO warrantyDTO);
    public List<WarrantyDTO> getWarranties();
    public WarrantyDTO getWarrantyByProduct(Long id);
}

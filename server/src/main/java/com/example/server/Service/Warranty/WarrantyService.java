package com.example.server.Service.Warranty;

import com.example.server.Pojo.Warranty;
import com.example.server.Repository.IWarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyService implements IWarrantyService{
    @Autowired
    IWarrantyRepository iWarrantyRepository;

    public boolean createWarranty(Warranty warranty){
        try{
            iWarrantyRepository.save(warranty);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public List<Warranty> getWarranties(){
        try{
            return (List<Warranty>) iWarrantyRepository.findAll();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

}

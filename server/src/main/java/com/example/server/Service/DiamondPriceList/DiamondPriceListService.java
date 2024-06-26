package com.example.server.Service.DiamondPriceList;

import com.example.server.Model.DiamondPriceListDTO;
import com.example.server.Pojo.*;
import com.example.server.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiamondPriceListService implements IDiamondPriceListService {
    @Autowired
    IDiamondPriceListRepository iDiamondPriceListRepository;

    @Autowired
    IClarityRepository iClarityRepository;

    @Autowired
    IColorRepository iColorRepository;

    @Autowired
    ICutRepository iCutRepository;

    @Autowired
    IOriginRepository iOriginRepository;

    @Override
    public DiamondPriceList getDiamondPriceListBy4C(double carat, Long clarity_id, Long color_id, Long cut_id, Long origin_id) throws ClassNotFoundException {
        Optional<DiamondPriceList> diamondPriceList = iDiamondPriceListRepository.findByCaratAndClarity_idAndColor_idAndCut_idAndOrigin_idAndCaratBetweenCaratFromAndCaratTo(carat, clarity_id, color_id, cut_id, origin_id);
        return diamondPriceList.orElseThrow(() -> new ClassNotFoundException("Not Found Diamond Price List by 4C"));
    }

    @Override
    public List<DiamondPriceListDTO> getDiamondsPriceList() {
        List<DiamondPriceListDTO> diamondPriceListDTOS = new ArrayList<>();
        Iterable<DiamondPriceList> diamondPriceLists = iDiamondPriceListRepository.findAll();
        diamondPriceLists.forEach((diamondPriceList -> {
            DiamondPriceListDTO diamondPriceListDTO = new DiamondPriceListDTO();
            diamondPriceListDTO.setId(diamondPriceList.getId());
            diamondPriceListDTO.setCaratFrom(diamondPriceList.getCaratFrom());
            diamondPriceListDTO.setCaratTo(diamondPriceList.getCaratTo());
            diamondPriceListDTO.setEffDate(diamondPriceList.getEffDate());
            diamondPriceListDTO.setCut(diamondPriceList.getCut().getCut());
            diamondPriceListDTO.setColor(diamondPriceList.getColor().getColor());
            diamondPriceListDTO.setClarity(diamondPriceList.getClarity().getClarity());
            diamondPriceListDTO.setOrigin(diamondPriceList.getOrigin().getOrigin());
            diamondPriceListDTO.setPrice(diamondPriceList.getPrice());
            diamondPriceListDTOS.add(diamondPriceListDTO);
        }));
        return diamondPriceListDTOS;
    }
    @Override
    public boolean updateDiamondPrice(DiamondPriceListDTO diamondPriceListDTO) {
        Optional<DiamondPriceList> diamondPriceList = iDiamondPriceListRepository.findById(diamondPriceListDTO.getId());
        try {
            diamondPriceList.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found by id: " + diamondPriceListDTO.getId()));
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        DiamondPriceList diamondPriceListSave = new DiamondPriceList();
        diamondPriceListSave.setId(diamondPriceListDTO.getId());
        diamondPriceListDTO.setCaratFrom(diamondPriceListDTO.getCaratFrom());
        diamondPriceListDTO.setCaratTo(diamondPriceListDTO.getCaratTo());
        diamondPriceListSave.setEffDate(diamondPriceListDTO.getEffDate());
        diamondPriceListSave.setPrice(diamondPriceListDTO.getPrice());

        Optional<Clarity> clarity = Optional.ofNullable(iClarityRepository.getClarityByClarity(diamondPriceListDTO.getClarity()));
        Optional<Color> color = Optional.ofNullable(iColorRepository.getColorByColor(diamondPriceListDTO.getColor()));
        Optional<Cut> cut = Optional.ofNullable(iCutRepository.getCutByCut(diamondPriceListDTO.getCut()));
        Optional<Origin> origin = Optional.ofNullable(iOriginRepository.getOriginByOrigin(diamondPriceListDTO.getOrigin()));
        try{
            clarity.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getClarity()));
            color.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getColor()));
            cut.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getCut()));
            origin.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getOrigin()));
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        diamondPriceListSave.setClarity(clarity.get());
        diamondPriceListSave.setColor(color.get());
        diamondPriceListSave.setCut(cut.get());
        diamondPriceListSave.setOrigin(origin.get());
        iDiamondPriceListRepository.save(diamondPriceListSave);
        return true;
    }

    public boolean addDiamondPriceList(DiamondPriceListDTO diamondPriceListDTO){
        DiamondPriceList diamondPriceListSave = new DiamondPriceList();
        diamondPriceListSave.setId(diamondPriceListDTO.getId());
        diamondPriceListDTO.setCaratFrom(diamondPriceListDTO.getCaratFrom());
        diamondPriceListDTO.setCaratTo(diamondPriceListDTO.getCaratTo());
        diamondPriceListSave.setEffDate(diamondPriceListDTO.getEffDate());
        diamondPriceListSave.setPrice(diamondPriceListDTO.getPrice());

        Optional<Clarity> clarity = Optional.ofNullable(iClarityRepository.getClarityByClarity(diamondPriceListDTO.getClarity()));
        Optional<Color> color = Optional.ofNullable(iColorRepository.getColorByColor(diamondPriceListDTO.getColor()));
        Optional<Cut> cut = Optional.ofNullable(iCutRepository.getCutByCut(diamondPriceListDTO.getCut()));
        Optional<Origin> origin = Optional.ofNullable(iOriginRepository.getOriginByOrigin(diamondPriceListDTO.getOrigin()));
        try{
            clarity.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getClarity()));
            color.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getColor()));
            cut.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getCut()));
            origin.orElseThrow(() -> new ClassNotFoundException("Diamond Price List Not Found By" + diamondPriceListDTO.getOrigin()));

            diamondPriceListSave.setClarity(clarity.get());
            diamondPriceListSave.setColor(color.get());
            diamondPriceListSave.setCut(cut.get());
            diamondPriceListSave.setOrigin(origin.get());
            iDiamondPriceListRepository.save(diamondPriceListSave);
            return true;
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

}

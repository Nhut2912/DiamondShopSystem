import React, { useMemo, useState } from 'react'
import { ICONS } from '../../constants/admin/index'
import InputSelectBox from './InputSelectBox'
import InputDoubleBox from './InputDoubleBox'

function AddMaterial({index,
    handleDeleteMaterial,
    materialProducts,
    setMaterialProducts,
    material}) {

  const [type, setType] = useState(null);
  const [weight, setWeight] = useState(null);
 
  const materialProduct = useMemo(() => (
    {
        "Type" : type === null ? material[0].name : type,
        "Weight" : weight
     }
    ),[type,weight]);

    const updatedMaterial = useMemo(() => {
        const _updateMaterialProduct = [...materialProducts];
        _updateMaterialProduct[index] = materialProduct;
        return _updateMaterialProduct;
    },[materialProduct]);

    setMaterialProducts(updatedMaterial);




  return (
    <ul>
            <li>
                {index+1}
            </li>
            <li>
    
                <InputSelectBox setParams={setType} 
                  
                    
                options={material} />
            </li>
            <li>
                <InputDoubleBox  _width="100px"  
               
                setParams={setWeight}  />
            </li>
            <li>
                {
                index >= 1 ? 
                <div className='delete-material-in-product' onClick={() => handleDeleteMaterial(index)} >
                    <img src={ICONS.icon_delete} alt=''/>
                </div> : ''
                }
                
                
            </li>
    </ul>
  )
}

export default AddMaterial
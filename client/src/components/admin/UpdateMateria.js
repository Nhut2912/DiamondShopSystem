import React, { useEffect, useMemo, useState } from 'react'
import { ICONS } from '../../constants/admin/index'
import InputSelectBox from './InputSelectBox'
import InputDoubleBox from './InputDoubleBox'
import '../../theme/admin/UpdateMaterial.css';


function UpdateMaterial({index,
    handleDeleteMaterial,
    materialProducts,
    setMaterialProducts,
    material}) {

  const [type, setType] = useState(materialProducts[index].name !== null ?materialProducts[index].name : null );
  const [weight, setWeight] = useState(materialProducts[index].weight !== null ?materialProducts[index].weight : null);

  const materialProduct = useMemo(() => (
    {
        "name" : type === null ? material[0].name : type,
        "weight" : weight
     }
    ),[type,weight]);
    const updatedMaterial = useMemo(() => {
        const _updateMaterialProduct = [...materialProducts];
        _updateMaterialProduct[index] = materialProduct;
        return _updateMaterialProduct;
    },[materialProduct,materialProducts]);

    useEffect(() => {
      setMaterialProducts(updatedMaterial)
    },[type,weight]);




  return (
    <ul>
            <li>
                {index+1}
            </li>
            <li>
                <InputSelectBox setParams={setType} 
                  getParams={materialProducts[index].name}
                options={material} />
            </li>
            <li>
                <InputDoubleBox  _width="100px"  
                getParams={materialProducts[index].weight}
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

export default UpdateMaterial
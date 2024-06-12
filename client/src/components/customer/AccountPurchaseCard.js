import React, { useEffect, useState } from 'react'
import '../../theme/customer/AccountPurchaseCard.css'
import ConvertLocalDateToFormat from '../../function/ConvertLocalDateToFormat';
import { getDownloadURL, ref } from 'firebase/storage';
import { imageStorage } from '../../config/FirebaseConfig';
import { useNavigate, useParams } from 'react-router-dom';



const numberFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 2,
  });


function AccountPurchaseCard({
        orderID,isDelivery,totalPrice,ordeDate,orderStatus
    }) {



    const COLORS_STATUS = [
        {name: "PENDING" , color : "#F2C438"},
        {name: "PREPARING" , color : "#F2A20C"},
        {name: "PERPARED", color : "#F2A20C"},
        {name : "DELIVERING", color : "#F25D07"},
        {name : "CANCELLED", color : "#D9043D"},
        {name : "COMPLETED", color : "#008F00"}
    ]
 
 const navigate = useNavigate();

 const [data,setData] = useState();
 const [imagesProduct,setImagesProduct] = useState();
 const [color,setColor] = useState();
 const [paid,setPaid] = useState();

 useEffect(() => {
    COLORS_STATUS.map((item) => {
        if(item.name === orderStatus){
            setColor(item.color);
        }
      })
 },[])

 useEffect(() => {
    fetch(`http://localhost:8080/api/payment?order_id=${orderID}`)
    .then((response) => response.json())
    .then( ( result ) => {
        if(result === null){
            setPaid(0);
        }else{
            let paidTmp = 0;
            result.map((item) => {
                paidTmp += item.amount;
            })
            setPaid(paidTmp);
        }

    }).catch((error) => console.error(error));
 },[])

 useEffect(() => {

        fetch(`http://localhost:8080/api/order_detail?order_id=${orderID}`)
        .then((response) => response.json())
        .then((result) => {
            if(result !== null){
                setData(result)
            }
        })
        .catch((error) => console.error(error));

  },[])

  useEffect(()=> {
    if(data !== undefined && data !== null){
        
        let getImageProduct =[];
        data.forEach(element => {
            getImageProduct = [...getImageProduct,element.product.images[0].url];
        });

        const getImageUrls = async () => {
              const imageTmpProduct = [];
              try {
                for (const image of getImageProduct) {
                  const imageRef = ref(imageStorage, image);
                  const url = await getDownloadURL(imageRef);
                  imageTmpProduct.push(url);
                }
              setImagesProduct(imageTmpProduct)
              } catch (error) {
                console.error('Error fetching image URLs:', error);
              }
        };
          
         getImageUrls(); 
          
    }
  },[data])

  if(data === undefined && data === null) return <div>Loading...</div>;
  let count = 0;

  const handlePayRemainder = () => {
     navigate("/account/purchase/remainder/"+orderID);
  }


  return (
    <div className='account-purchase-card-container'>
        <p>{
        ordeDate !== undefined && 
        ordeDate !== null ?
        ConvertLocalDateToFormat(ordeDate) 
       : null}
        </p>
        <div className='purchase-card'>
            <div>
                <h3>#{orderID !== undefined && orderID !== null ?
                orderID : null }</h3>
                <h3>
                {isDelivery !== undefined && isDelivery !== null && 
                isDelivery === true ? "DELIVERY" : "PICK UP IN STORE" }
                </h3>
            </div>

            {   
            data !== undefined && data !== null &&
                data.map((element) =>(

                <div className='product-purchase'>
                    <div>
                        <img src={ imagesProduct !== undefined && imagesProduct !== null ? imagesProduct[count++] : null} alt='' />
                    </div>
                    <div>
                        <h3>{data !== undefined && data !== null ? element.product.name : null}</h3>
                        <span>Size : {element.size}</span>
                        <span>Price : {numberFormatter.format(element.priceAfterSizeAdjustment)}</span>
                    </div>
                </div>
        
                    
                ))
            }
          
            <div>
                <h4>Total : {numberFormatter.format(totalPrice)}  <span>{`( Paid ${numberFormatter.format(paid !== undefined && paid !== null ? paid : 0)} of ${numberFormatter.format(totalPrice)} )`}</span></h4>
                <h4  style={
                    {
                        color: color !== undefined && color !== null ? 
                        color : null
                    }
                } >
                {orderStatus !== undefined && orderStatus !== null ? 

                orderStatus 
                
                : null }
                </h4>
            </div>
        </div>

        {
            orderStatus !== undefined && orderStatus !== null && paid !== undefined && paid !== null &&
            totalPrice !== undefined && totalPrice !== null &&
             paid.toFixed(2)  !== totalPrice.toFixed(2)  &&
            (orderStatus !== "PENDING" && orderStatus !== "COMPLETED" && orderStatus !== "CANCELLED") ?
            
            <div className='remainder-paid'>
                <div

                    onClick={handlePayRemainder}

                >
                    <span   >Pay Remainder</span>
                </div>
            </div>
        : ""
        }
    </div>
  )
}

export default AccountPurchaseCard
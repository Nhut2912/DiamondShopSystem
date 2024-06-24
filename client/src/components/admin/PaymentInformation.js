import React, { useEffect, useState } from 'react'

import '../../theme/admin/PaymentInformation.css'
import ConvertLocalDateToFormat from '../../function/ConvertLocalDateToFormat';
import { imageStorage } from '../../config/FirebaseConfig';
import { getDownloadURL, ref } from 'firebase/storage';


function PaymentInformation({paymentTitle,statusPayment,
        paymentId,
        paymentMethod,paymentDate,paymentAmount,
        paymentFEE,
        statusOrder,paymentImage,orderID
    }) {
    

  console.log(statusPayment);

  const COLORS_STATUS_PAYMENT = [
    {name : "PENDING", color:"rgba(217, 179, 132, 1)"},
    {name : "NOT YET", color: "rgba(0, 0, 0, 0.4)"},
    {name : "DONE", color: "rgb(54, 227, 57)"}
  ]

  const [colorActive,setColorActive] = useState();
  const [imagePayment,setImagePayment] = useState();

  useEffect(()=> {
    COLORS_STATUS_PAYMENT.map((item) => {
        console.log(statusPayment);
        console.log(statusPayment === item.name);
        if(item.name === statusPayment){
            setColorActive(item.color);
        }
      })
  },[]);

  useEffect(()=> {
    if(paymentImage !== undefined && paymentImage !== null){
        const getImageUrls = async () => {
            try {
                const imageRef = ref(imageStorage, paymentImage);
                const url = await getDownloadURL(imageRef);
                setImagePayment(url);
            } catch (error) {
              console.error('Error fetching image URLs:', error);
            }
        };
        getImageUrls();
    }
  },[])
  if(colorActive === undefined) return <div>Loading</div>
  if(imagePayment ===  undefined  && paymentImage !== undefined && paymentImage !== null ) return <div>Loading</div>
  


  const handleAcceptPayment = () => {
    console.log(paymentTitle);
    if(paymentTitle === "DEPOSIT PAYMENT"){
        const requestOptions = {
            method: "POST",
            redirect: "follow"
          };
          
          fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/order/Preparing/${orderID}`, requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
            .catch((error) => console.error(error));
    }
  }

  const handleCanceledPayment = () => {
    const requestOptions = {
        method: "POST",
        redirect: "follow"
      };
      
      fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/order/Canceled/${orderID}`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
  }

  return (
    <div className='deposit-payment'>
    <h3>{paymentTitle}
        <span
            style={{color : colorActive,
                borderColor: colorActive
            }}
        >{statusPayment}</span>
    </h3>
    <ul className='method-payment-deposit'>
        <li>
            <h4>PaymentID</h4>
            <span>
                {paymentId === "" ? "--:--" : "#" + paymentId}
            </span>
        </li>
        <li>
            <h4>Payment method</h4>
            <span>{paymentMethod === "" ? "--:--" : paymentMethod}</span>
        </li>
        <li>
            <h4>Payment Date</h4>
            <span>{paymentDate === "" || paymentDate === null ? "--:--" : ConvertLocalDateToFormat(paymentDate)}</span>
        </li>
        <li>
            <h4>Payment Amount</h4>
            <span>{paymentAmount === "" ? "--:--" : "$"+paymentAmount}</span>
        </li>
       
        {
            paymentMethod === "BANKTRANSFER" 
            && paymentTitle === "DEPOSIT PAYMENT" 
            && statusOrder === "PENDING" && (statusPayment !== "DONE" && statusPayment !== "NOT YET")  ?
            <li className='invoice-customer'>
                <h4>Invoice Of Customer</h4>
                <div className='invoice'>
                    <img src={imagePayment !== undefined && imagePayment !== null ?
                        imagePayment : null
                    } alt=''/>
                </div>
            </li>: ""
        }
        {
            paymentMethod === "BANKTRANSFER" 
            && paymentTitle !== "DEPOSIT PAYMENT" 
            && statusOrder !== "PENDING" &&  (statusPayment !== "DONE" && statusPayment !== "NOT YET")  ?
            <li className='invoice-customer'>
                <h4>Invoice Of Customer</h4>
                <div className='invoice'>
                    <img src={imagePayment !== undefined && imagePayment !== null ?
                        imagePayment : null
                    } alt=''/>
                </div>
            </li>: ""
        }
       

        {
            paymentTitle === "DEPOSIT PAYMENT" ? 
            <li>
                <h4>Deposit Fee</h4>
                <span>${paymentFEE}</span>
            </li>
            : <li>
                <h4>Remainder Fee</h4>
                <span>
                    ${paymentFEE}
                </span>
            </li>
        }

       

    </ul>

    {
            paymentMethod === "BANKTRANSFER" 
            && paymentTitle === "DEPOSIT PAYMENT"
             && statusOrder === "PENDING" && (statusPayment !== "DONE" && statusPayment !== "NOT YET") ?
            <div className='button-accept-payment'>
                <div className='accept'
                    onClick={ handleAcceptPayment}
                >
                    ACCEPT PAYMENT
                </div>
                <div
                    onClick={handleCanceledPayment}
                >
                    CANCEL PAYMENT
                </div >
            </div>: ""
        }
        {
            paymentMethod === "BANKTRANSFER"
             && paymentTitle !== "DEPOSIT PAYMENT" 
             && statusOrder !== "PENDING" &&
             (statusPayment !== "DONE" && statusPayment !== "NOT YET")  ?
            <div className='button-accept-payment'>
                <div className='accept'>
                    ACCEPT PAYMENT
                </div>
                <div>
                    CANCEL PAYMENT
                </div>
            </div>: ""
        }

  



</div>
  )
}

export default PaymentInformation
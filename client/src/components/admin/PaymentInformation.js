import React from 'react'

import '../../theme/admin/PaymentInformation.css'


function PaymentInformation({paymentTitle,statusPayment,
        paymentId,
        paymentMethod,paymentDate,paymentAmount,
        paymentFEE,
        statusOrder
    }) {
    

  return (
    <div className='deposit-payment'>
    <h3>{paymentTitle}
        <span>{statusPayment}</span>
    </h3>
    <ul className='method-payment-deposit'>
        <li>
            <h4>PaymentID</h4>
            <span>
                {paymentId === "" ? "--:--" : paymentId}
            </span>
        </li>
        <li>
            <h4>Payment method</h4>
            <span>{paymentMethod === "" ? "--:--" : paymentMethod}</span>
        </li>
        <li>
            <h4>Payment Date</h4>
            <span>{paymentDate === "" ? "--:--" : paymentDate}</span>
        </li>
        <li>
            <h4>Payment Amount</h4>
            <span>{paymentAmount === "" ? "--:--" : paymentAmount}</span>
        </li>
       
        {
            paymentMethod === "BANK TRASNFER" 
            && paymentTitle === "DEPOSIT PAYMENT" 
            && statusOrder === "PENDING" && (statusPayment !== "DONE" && statusPayment !== "NOT YET")  ?
            <li className='invoice-customer'>
                <h4>Invoice Of Customer</h4>
                <div className='invoice'>

                </div>
            </li>: ""
        }
        {
            paymentMethod === "BANK TRASNFER" 
            && paymentTitle !== "DEPOSIT PAYMENT" 
            && statusOrder !== "PENDING" &&  (statusPayment !== "DONE" && statusPayment !== "NOT YET")  ?
            <li className='invoice-customer'>
                <h4>Invoice Of Customer</h4>
                <div className='invoice'>

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
            paymentMethod === "BANK TRASNFER" 
            && paymentTitle === "DEPOSIT PAYMENT"
             && statusOrder === "PENDING" && (statusPayment !== "DONE" && statusPayment !== "NOT YET") ?
            <div className='button-accept-payment'>
                <div className='accept'>
                    ACCEPT PAYMENT
                </div>
                <div>
                    CANCEL PAYMENT
                </div>
            </div>: ""
        }
        {
            paymentMethod === "BANK TRASNFER"
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
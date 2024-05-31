import React, { useState } from 'react'

import '../../theme/customer/Home.css'
import { IMAGES } from '../../constants/customer';
import ProductCard from '../../components/customer/ProductCard';
import Question from '../../components/customer/Question';
import ProductSaleBanner from '../../components/customer/ProductSaleBanner';


function Home() {

  const [itemActive,setItemActive] = useState("New Arrivals");

  const [product,setProduct] = useState(8);

  const navigation = [
    {name : "New Arrivals" },
    {name : "On Sale"}
  ]  

  const handleClick = (item) => {
    setItemActive(item);
  }



  return (
    <div className='container-home-pages'>
        <div className='banner-shop'>
            <img src={IMAGES.image_banner}  alt=''/>
        </div>
        <div className='category'>
            <div>
                <div className='jewelry-set'>
                      <h1>
                        JEWELRY SET
                      </h1>
                      <div>
                          <span>THE MOMENT</span>
                      </div>
                      <span>
                          See the collection
                      </span>
                      <img src={IMAGES.image_the_momment_set} alt='' className='img-category' />
                </div>
                <div className='jewelry ring'>
                      <h2>Ring</h2>
                      <span>See all the product</span>
                      <img src={IMAGES.image_ring} alt='' className='img-category' />          

                </div>
                <div className='jewelry earing' >
                     <h2>Earring</h2>
                     <span>See all the product</span>
                     <img src={IMAGES.image_earing} alt='' className='img-category' />
                </div>
            </div>
            <div>
                <div className='jewelry pendant'>
                      <h2>Pendant</h2>
                     <span>See all the product</span>
                     <img src={IMAGES.image_pendant} alt='' className='img-category' />
                </div>
                <div className='jewelry shake'>
                    <h2>Shake</h2>
                     <span>See all the product</span>
                     <img src={IMAGES.image_shake} alt='' className='img-category' />
                </div>
                <div  className='jewelry-set' >
                      <h1>
                        JEWELRY SET
                      </h1>
                      <div>
                          <span>THE MOMENT</span>
                      </div>
                      <span>
                          See the collection
                      </span>
                      <img src={IMAGES.image_the_beauty_set} alt='' className='img-category' />
                </div>
            </div>
        </div>

        <div className='product-newarrival-onsale'>
            <ul>
                {
                    navigation.map((item) => (
                        <li 
                            className={itemActive === item.name ? 'isActive' : ''}
                            onClick={() => handleClick(item.name)}
                        >
                            {item.name}
                        </li>
                    ))
                }
            </ul>
            
            <div className='home-product-container'>
                    <div className='button-see-more'>
                        <span>See More</span>
                    </div>
                    <div className='home-product-container-card'>
                        {Array(product).fill(0).map((item) => (
                            <ProductCard />
                        ))}
                          
                    </div>
            </div>

        </div>
        <ProductSaleBanner />
        <Question />
        <div className='did-not-find-answer'>
             <div>
                <h1>YOU DID NOT FIND THE ANSER</h1>
                <p>
                We are very sorry for this inconvenience, please contact us and we will respond to you immediately
                </p>
                <div>
                    <span>CONTACT</span>
                </div>
             </div>
        </div>
    </div>
  )
}

export default Home
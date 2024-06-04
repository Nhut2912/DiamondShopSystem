import { useState,createContext } from "react";
import useLocalStorage from "../hook/useLocalStorage";

const CartContext = createContext();

function CartProvider({ children }){
    const [account,setAccount] = useLocalStorage("account",localStorage.getItem("account"));
    const [cart,setCart] = useState(account === null ? 0 : account.cart.length);

    const addToCart = () => {
        const number = JSON.parse(localStorage.getItem("account")).cart.length;
        setCart(number);
    }

    const value = {
        cart, addToCart
    }

    return(
        <CartContext.Provider value={value}>
            {children}
        </CartContext.Provider>
    )
}

export {CartContext,CartProvider};
import React, { Children, createContext, useState } from 'react'

const UpdatePromotionContext = createContext();


function UpdateContextProvider({children}) {
 const [update,setUpdate] = useState();

 const value ={
    update,setUpdate
 }
  return (
    <UpdatePromotionContext.Provider value={value}>
        {children}
    </UpdatePromotionContext.Provider>
  )
}

export {UpdatePromotionContext,UpdateContextProvider}
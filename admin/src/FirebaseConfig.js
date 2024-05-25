
import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyCn-FTeycT20Q9YZJFUowV1LORNraWE3qU",
  authDomain: "diamondshopsystem-66df2.firebaseapp.com",
  projectId: "diamondshopsystem-66df2",
  storageBucket: "diamondshopsystem-66df2.appspot.com",
  messagingSenderId: "1012909276694",
  appId: "1:1012909276694:web:80ad9da5031015c9ee7f37",
  measurementId: "G-EZH1KD2DHQ"
};


const app = initializeApp(firebaseConfig);
export const imageStorage = getStorage(app);
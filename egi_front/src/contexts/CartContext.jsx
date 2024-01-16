import React, { useContext, useState, useEffect } from "react";
import { createContext } from "react";
import { ProductData } from "../Website";

export const CartContext = createContext();

export const useData = () => useContext(CartContext);

export function CartProvider({ children }) {
  const [cart, setCart] = useState([]);
  const [sum, setSum] = useState(0);
  const [totalItems, setTotalItems] = useState(0);
  const [products, setProducts] = useState(ProductData);

  const removeFromCart = (product) => {
    // This should only be accessed if the item is first in the cart.
    let updatedCart = cart.map((item) => {
      if (item.id === product.id) {
        return { ...item, cartQuantity: item.cartQuantity - 1 };
      }
      return {};
    });
    setCart(updatedCart);
    findSum(updatedCart);
    findTotalItems(updatedCart);
  };

  const addToCart = (product) => {
    let productWasAdded = false;
    let updatedCart = cart.map((item) => {
      if (item.id === product.id) {
        productWasAdded = true;
        return { ...item, cartQuantity: item.cartQuantity + 1 };
      }
      return { ...item };
    });

    if (!productWasAdded) {
      updatedCart = [...cart, { ...product, cartQuantity: 1 }];
    }
    setCart(updatedCart);
    findSum(updatedCart);
    findTotalItems(updatedCart);
  };

  const findTotalItems = (newCart) => {
    const totalItems = newCart.reduce(
      (total, product) => total + product.cartQuantity,
      0
    );

    setTotalItems(totalItems);
  };

  const findSum = (newCart) => {
    const sum = newCart.reduce(
      (total, product) => total + product.price * product.cartQuantity,
      0
    );
    const formattedSum = sum.toFixed(2);
    setSum(formattedSum);
  };

  const fetchProducts = async () => {
    try {
      // const productData = await getProducts();
      // setProducts(productData);
    } catch (error) {
      console.error("Error fetching product data:", error);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  return (
    <CartContext.Provider
      value={{
        cart,
        setCart,
        products,
        addToCart,
        sum,
        totalItems,
        removeFromCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
}

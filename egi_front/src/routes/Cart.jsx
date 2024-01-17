import React, { useContext } from "react";
import { CartContext } from "../contexts/CartContext";
import { WebsiteColors } from "../Website";
import { faPlus, faMinus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";

function NssButtonAddSubtract(props) {
  const { onAdd, onSubtract, disableSubtract, disableAdd, cartQuantity } =
    props;

  const getButtonColor = (disabled) => {
    if (disabled) {
      return "bg-black text-white";
    }
    if (!disabled) {
      return WebsiteColors.buttonColors;
    }
  };

  return (
    <div className="flex flex-col items-center justify-center">
      <button
        type="button"
        className={`${getButtonColor(
          disableAdd
        )} inline-flex items-center px-4 py-1 font-semibold leading-6 text-sm shadow rounded-t-xl transition ease-in-out duration-500 cursor-pointer`}
        onClick={onAdd}
        disabled={disableAdd}
      >
        <div>
          <FontAwesomeIcon icon={faPlus} />
        </div>
      </button>
      <div>{cartQuantity}</div>
      <button
        type="button"
        className={`${getButtonColor(
          disableSubtract
        )} inline-flex items-center px-4 py-1 font-semibold leading-6 text-sm shadow rounded-b-xl transition ease-in-out duration-500 cursor-pointer`}
        onClick={onSubtract}
        disabled={disableSubtract}
      >
        <div>
          <FontAwesomeIcon icon={faMinus} />
        </div>
      </button>
    </div>
  );
}

const CartItem = (props) => {
  const cartContext = useContext(CartContext);
  const { product } = props;

  const onAdd = () => {
    cartContext.addToCart(product);
  };

  const onSubtract = () => {
    cartContext.removeFromCart(product);
  };

  return (
    <div className="text-white">
      <div className="border-2 border-egi-50 bg-egi-60 rounded-sm p-1">
        <div className="flex flex-col justify-between">
          <div className="p-2">
            <div className="flex justify-between">
              <div className="w-10/12">
                <div className="font-chewy text-2xl xl:text-5xl">
                  {product.name}
                </div>
                <div className="pr-2 xl:text-2xl pt-2">
                  {product.description}
                </div>
              </div>
              <div className="w-1/12">
                <img
                  className="border border-egi-20 rounded-lg object-contain"
                  src={product.image}
                ></img>
              </div>
              <div>
                <NssButtonAddSubtract
                  onAdd={onAdd}
                  onSubtract={onSubtract}
                  disableSubtract={product.cartQuantity <= 0 ? true : false}
                  disableAdd={
                    product.cartQuantity >= product.quantity ? true : false
                  }
                  cartQuantity={product.cartQuantity}
                />
              </div>
            </div>
            <div className="flex justify-between xl:text-2xl pt-2">
              <div>Price: ${product.price.toFixed(2)}</div>
              <div>Available Units: {product.quantity}</div>
              <div>Amount in Cart: {product.cartQuantity}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

function Cart() {
  const cartContext = useContext(CartContext);
  const navigate = useNavigate();
  const proceedToCheckout = () => {};

  const goToFaqs = () => {
    navigate("/faqs");
  };

  const goToProducts = () => {
    navigate("/products");
  };

  const checkoutButtonText = () => {
    return "Proceed to Checkout (" + cartContext.totalItems + " items)";
  };

  const cartTotalCost = () => {
    return "Cart Total: $" + cartContext.sum;
  };

  return (
    <div className="p-2">
      <div className="flex justify-between p-2">
        <div>
          <div className="font-chewy text-2xl">
            Have questions about these products?
          </div>
          <div className="text-center">
            <button
              onClick={goToFaqs}
              type="button"
              className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
              disabled=""
            >
              Go to FAQs
            </button>
          </div>
        </div>
        <div>
          <div className="font-chewy text-2xl">Not finished shopping?</div>
          <div className="text-center">
            <button
              onClick={goToProducts}
              type="button"
              className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
              disabled=""
            >
              Go to Products
            </button>
          </div>
        </div>
      </div>
      <div className="flex content-center justify-center text-center gap-2">
        <div className="mt-auto mb-auto text-2xl">{cartTotalCost()}</div>
        <div>
          <button
            onClick={proceedToCheckout}
            type="button"
            className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
            disabled=""
          >
            {checkoutButtonText()}
          </button>
        </div>
      </div>
      <div>
        <div className="grid grid-cols-1 gap-1 pt-1">
          {cartContext.cart.map((item) => (
            <CartItem product={item} key={item.id} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Cart;

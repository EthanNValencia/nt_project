import React, { useContext } from "react";
import { WebsiteColors } from "../Website";
import { CartContext } from "../contexts/CartContext";
import { useNavigate } from "react-router-dom";
import { faShoppingCart, faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Product = (props) => {
  const cartContext = useContext(CartContext);
  const navigate = useNavigate();
  const { product } = props;

  const addToCard = () => {
    console.log(product.id);
    cartContext.addToCart(product);
  };

  const goToCart = () => {
    navigate("/cart");
  };

  return (
    <div className="">
      <div className="border-2 border-egi-20 bg-egi-30 rounded-sm p-2 shadow-lg">
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
              <div className="w-2/12">
                <img
                  className="border border-egi-20 rounded-lg object-contain"
                  src={product.image}
                ></img>
              </div>
            </div>
            <div className="flex justify-between xl:text-2xl pt-2">
              <div>Price: ${product.price.toFixed(2)}</div>
              <div>Available Units: {product.quantity}</div>
            </div>
          </div>
          <div className="flex justify-between">
            <div>
              <button
                onClick={addToCard}
                type="button"
                className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
                disabled=""
              >
                <FontAwesomeIcon icon={faPlus} />
                &nbsp;Add to Cart
              </button>
            </div>
            <div>
              <button
                onClick={goToCart}
                type="button"
                className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
                disabled=""
              >
                <FontAwesomeIcon icon={faShoppingCart} />
                &nbsp; Go to Cart
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

function Products() {
  const cartContext = useContext(CartContext);

  return (
    <div className={`${WebsiteColors.productsTextColor} bg-egi-60`}>
      <div className="p-2">
        <div className="text-white font-montserrat text-2xl p-2">
          Discover a world of sustainable elegance with our eco-friendly
          lighting solutions. Illuminate your surroundings responsibly and
          stylishly with SolarGlow Lamps, harnessing the sun's power to brighten
          your garden, patio, or pathway while reducing your carbon footprint.
          Embrace the enchanting glow of WindBrite Lanterns, powered by the
          gentle winds to create a mesmerizing ambiance in your outdoor spaces.
          Transform your workspace into a beacon of sustainability with
          HydroBeam Desk Lamps, where cutting-edge technology meets
          eco-conscious design. Elevate your interior decor with BambooGlo
          Pendants, crafted from sustainable bamboo for a touch of natural
          elegance. Choose our lighting solutions – not just to light up your
          spaces, but to make a conscious choice for a brighter, greener
          tomorrow. Embrace style with a conscience, and let our lights guide
          you towards a sustainable and enchanting future.
        </div>
        <div className="grid grid-cols-1 gap-2">
          {cartContext.products.map((product) => (
            <Product product={product} key={product.id} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Products;

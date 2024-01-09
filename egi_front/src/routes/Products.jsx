import React from "react";
import { ProductData } from "../Website";
import { WebsiteColors } from "../Website";

const Product = (props) => {
  const addToCard = () => {
    console.log(product.id);
  };

  const { product } = props;
  return (
    <div className="border-2 border-egi-20 bg-egi-30 rounded-sm w-1/2 p-2">
      <div className="flex flex-col">
        <div className="p-2">
          <div className="flex justify-between">
            <div>
              <div className="font-chewy text-2xl">{product.name}</div>
              <div>{product.description}</div>
            </div>
            <div className="w-96">
              <img
                className="w-96 border border-egi-20 rounded-lg"
                src={product.image}
              ></img>
            </div>
          </div>
          <div className="flex justify-between">
            <div>Price: ${product.price.toFixed(2)}</div>
            <div>Available Units: {product.quantity}</div>
          </div>
        </div>
        <div className="p-1 mt-auto">
          <button
            onClick={addToCard}
            type="button"
            className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
            disabled=""
          >
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
};

function Products() {
  return (
    <div className="bg-egi-60 flex items-center justify-center">
      <div className="w-11/12">
        <div className="flex flex-wrap p-2">
          {ProductData.map((product) => (
            <Product product={product} key={product.id} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Products;

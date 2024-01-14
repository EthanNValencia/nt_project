import React from "react";
import { ProductData } from "../Website";
import { WebsiteColors } from "../Website";

const Product = (props) => {
  const addToCard = () => {
    console.log(product.id);
  };

  const { product } = props;
  return (
    <div className="">
      <div className="border-2 border-egi-20 bg-egi-30 rounded-sm p-2">
        <div className="flex flex-col justify-between">
          <div className="p-2">
            <div className="flex justify-between">
              <div className="w-10/12">
                <div className="font-chewy text-2xl">{product.name}</div>
                <div className="pr-2">{product.description}</div>
              </div>
              <div className="w-2/12">
                <img
                  className="border border-egi-20 rounded-lg object-contain"
                  src={product.image}
                ></img>
              </div>
            </div>
            <div className="flex justify-between">
              <div>Price: ${product.price.toFixed(2)}</div>
              <div>Available Units: {product.quantity}</div>
            </div>
          </div>
          <div>
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
    </div>
  );
};

function Products() {
  return (
    <div className={`${WebsiteColors.productsTextColor} bg-egi-60`}>
      <div className="p-2">
        <div className="grid grid-cols-1 gap-2">
          {ProductData.map((product) => (
            <Product product={product} key={product.id} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Products;

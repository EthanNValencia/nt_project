import React, { useContext, useEffect, useState } from "react";
import { PaymentElement } from "@stripe/react-stripe-js";
import { useStripe, useElements } from "@stripe/react-stripe-js";
import { WebsiteColors } from "../Website";

export default function CheckoutForm() {
  const stripe = useStripe();
  const elements = useElements();
  const [message, setMessage] = useState(null);
  const [isProcessing, setIsProcessing] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Here?");
    if (!stripe || !elements) {
      // Stripe.js has not yet loaded.
      // Make sure to disable form submission until Stripe.js has loaded.
      return;
    }

    setIsProcessing(true);
    console.log("Here??");
    const { error } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        // Make sure to change this to your payment completion page
        return_url: `${window.location.origin}/completion`,
      },
    });
    console.log("Here???");
    if (error.type === "card_error" || error.type === "validation_error") {
      setMessage(error.message);
    } else {
      setMessage("An unexpected error occured.");
    }

    setIsProcessing(false);
  };

  return (
    <div className="flex justify-center p-1">
      <div className="w-1/2 border shadow-xl p-2 border-egi-30">
        <form id="payment-form" onSubmit={handleSubmit}>
          <PaymentElement id="payment-element" />
          <div className="pt-2">
            <button
              className={`${WebsiteColors.buttonColors} inline-flex items-center px-8 py-2 font-semibold leading-6 text-sm shadow rounded-md first-line:transition ease-in-out duration-150 cursor-pointer`}
              disabled={isProcessing || !stripe || !elements}
              onClick={handleSubmit}
            >
              <span id="button-text">
                {isProcessing ? "Processing ... " : "Pay now"}
              </span>
            </button>
          </div>
          {/* Show any error or success messages */}
          {message && <div id="payment-message">{message}</div>}
        </form>
      </div>
    </div>
  );
}

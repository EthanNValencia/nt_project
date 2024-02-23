import React, { useContext, useEffect, useState } from "react";
import { PaymentElement } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import { getPublishedKey, getClientSecret } from "../axios/api";
import CheckoutForm from "./CheckoutForm";

const Payment = () => {
  const [stripePromise, setStripePromise] = useState(null);
  const [clientSecret, setClientSecret] = useState("");
  const [hasApiError, setHasApiError] = useState(false);

  useEffect(() => {
    async function fetchKey() {
      try {
        const key = await getPublishedKey();
        const stripe = await loadStripe(key);
        setStripePromise(stripe);
        const secret = await getClientSecret();
        setClientSecret(secret);
        setHasApiError(false);
      } catch (error) {
        setHasApiError(true);
      }
    }
    fetchKey();
  }, []);

  return (
    <form>
      <h1>Payment Element</h1>
      {stripePromise && clientSecret ? (
        <Elements stripe={stripePromise} options={{ clientSecret }}>
          <CheckoutForm />
        </Elements>
      ) : (
        <></>
      )}
    </form>
  );
};

export default Payment;

import React, { useState, useContext, useEffect } from "react";
import { UserContext } from "../../contexts/context";
import { useNavigate } from "react-router-dom";
import ContinueBack from "../../components/ContinueBack";

const FADE_INTERVAL_MS = 3000;
const WORD_CHANGE_INTERVAL_MS = FADE_INTERVAL_MS * 2;
const WORDS_TO_ANIMATE = [
  "You hurt, and you just don’t know what to do?",
  "Everyone’s giving you advice and you don’t know who to listen to?",
  " Tried Physical Therapy before and think it doesn’t work for you?",
  "We don’t know what we don’t know. That’s why we ask questions.",
  "Don’t stop doing what you love, no matter what your age!",
];

const TransitionText = () => {
  const [isFaded, setIsFaded] = useState(false);
  const [wordOrder, setWordOrder] = useState(0);

  useEffect(() => {
    const fadeTimeout = setInterval(() => {
      setIsFaded((prevIsFaded) => !prevIsFaded);
    }, FADE_INTERVAL_MS);

    return () => clearInterval(fadeTimeout);
  }, []);

  useEffect(() => {
    const wordTimeout = setInterval(() => {
      setWordOrder(
        (prevWordOrder) => (prevWordOrder + 1) % WORDS_TO_ANIMATE.length
      );
    }, WORD_CHANGE_INTERVAL_MS);

    return () => clearInterval(wordTimeout);
  }, []);

  return (
    <div className="text-center text-md mb-4 text-xl">
      <div
        className={`${
          isFaded
            ? "opacity-0 transition-opacity duration-1000 ease-in-out"
            : "transition-opacity duration-1500 ease-in-out"
        }`}
      >
        {WORDS_TO_ANIMATE[wordOrder]}
      </div>
    </div>
  );
};

/*

<div className="text-center text-md mb-4 text-xl">
              We’ll have about 20 minutes set aside for you.
            </div>

*/

// Step 1

function RequestName() {
  // Define state variables for form fields
  const [submitAttempted, setSubmitAttempted] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [fnValidated, setFnValidated] = useState(false);
  const [lastName, setLastName] = useState("");
  const [lnValidated, setLnValidated] = useState(false);
  const [continueIsEnabled, setContinueIsEnabled] = useState(false);
  const userContext = useContext(UserContext);
  const navigate = useNavigate();

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const validateInputFields = () => {
    let fnValid = false;
    let lnValid = false;
    if (firstName.length >= 3) {
      fnValid = true;
      setFnValidated(true);
    } else {
      fnValid = false;
      setFnValidated(false);
    }
    if (lastName.length >= 3) {
      lnValid = true;
      setLnValidated(true);
    } else {
      lnValid = false;
      setLnValidated(false);
    }
    if (fnValid && lnValid) {
      setContinueIsEnabled(true);
    } else {
      setContinueIsEnabled(false);
    }
  };

  function goBack() {
    navigate("/");
  }

  function onContinue() {
    setSubmitAttempted(true);

    if (fnValidated && lnValidated) {
      userContext.setUserName(firstName, lastName);
      userContext.setAppointmentName(firstName, lastName);
      navigate("/category");
    }
  }

  return (
    <div className="">
      <div className="bg-white px-2 pt-2 pb-2 mb-4 shadow-xl w-fit ring-1 ring-gray-900/5 rounded-lg tracking-tighter">
        <div className="text-center text-md mb-4 flex max-w-max">
          Welcome to Nephew Physical Therapy. This process will walk you through
          creating a tentative appointment. After your appointment is submitted
          we will reach out to you confirm your appointment.
        </div>
        <div>
          <TransitionText />
        </div>
        <div className="text-center text-md mb-4">
          Please begin by introducing yourself.
        </div>
        <form onSubmit={handleSubmit}>
          <div className="flex flex-col justify-center items-center">
            <div className="mb-4">
              {!fnValidated && submitAttempted ? (
                <div className="text-center text-red-400">
                  Please enter your first name.
                </div>
              ) : (
                <></>
              )}
              <label className="block text-gray-700 text-sm font-bold">
                Enter your first name:
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="firstName"
                type="text"
                placeholder="First name"
                value={firstName}
                onChange={(e) => {
                  setFirstName(e.target.value);
                  validateInputFields();
                }}
              />
            </div>
            <div className="mb-4">
              {!lnValidated && submitAttempted ? (
                <div className="text-center text-red-400">
                  Please enter your last name.
                </div>
              ) : (
                <></>
              )}
              <label className="block text-gray-700 text-sm font-bold">
                Enter your last name:
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="firstName"
                type="text"
                placeholder="Last name"
                value={lastName}
                onChange={(e) => {
                  setLastName(e.target.value);
                  validateInputFields();
                }}
              />
            </div>
            <div className="text-center text-md mb-4 flex max-w-max">
              Have a cup of coffee on us and let’s talk about what’s stopping
              you. We want to hear about your limitations, your aches and pains,
              and your goals. We want to answer your questions about PT. What an
              evaluation would look like? What treatments we might use for you?
              And get a feel for who we are. Ultimately, It’s our job to work
              ourselves out of a job, so our Therapy will focus on your personal
              accountability and long term success.
            </div>
          </div>
        </form>
      </div>
      <ContinueBack
        goBack={goBack}
        onContinue={onContinue}
        continueIsEnabled={continueIsEnabled}
      />
    </div>
  );
}

export default RequestName;

// {submittedName && <p>You submitted the name: {submittedName}</p>}

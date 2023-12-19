import React, { useState, useEffect } from "react";
import NtButton from "../components/NtButton";
import FAQsDiv from "../components/FAQsDiv";
import { postFaq, getAnsweredFAQs } from "../axios/api";
import ApiError from "../components/ApiError";
import { FAQs } from "../Website";

function FrequentlyAskedQuestions() {
  const [faqs, setFAQs] = useState([]);
  const [message, setMessage] = useState();
  const [loading, setLoading] = useState(false);
  const [questionSubmitted, setQuestionSubmitted] = useState(false);
  const [hasApiError, setHasApiError] = useState(false);

  async function onClick() {
    try {
      setLoading(true);
      const response = await postFaq(message);
      setLoading(false);
      setQuestionSubmitted(true);
      setHasApiError(false);
    } catch (error) {
      // console.error("Error submitting FAQ:", error);
      setHasApiError(true);
    }
  }

  useEffect(() => {
    async function fetchFaqs() {
      try {
        const data = await getAnsweredFAQs();
        // console.log(data);
        setFAQs(data);
        setHasApiError(false);
      } catch (error) {
        // console.error("Error loading FAQ:", error);
        setHasApiError(true);
      }
    }
    // fetchFaqs();
  }, []);

  return (
    <div className="p-4">
      <div className="grid grid-flow-row grid-cols-2 border-b-4 text-2xl tracking-tighter">
        <h1>Questions</h1>
        <h1>Answers</h1>
      </div>

      {FAQs.map((faq) => (
        <FAQsDiv faq={faq} key={faq.id} />
      ))}

      {/*
      {loading ? (
        <FAQsLoading />
      ) : (
        faqs.map((faq) => <FAQsDiv faq={faq} key={faq.id} />)
      )}
      */}

      <div className="mt-6 mb-4">
        <label className="block mb-2 text-xl font-medium text-gray-900">
          Was your question not answered? Please ask your question here.
        </label>

        <textarea
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          id="message"
          rows="3"
          className="block p-2.5 w-full text-sm text-gray-900 bg-gray-200 rounded-lg border border-gray-300 focus:ring-npt_colors-30 focus:border-npt_colors-30 "
          placeholder="Write your question here..."
          disabled={loading || questionSubmitted ? "disabled" : ""}
        ></textarea>
      </div>

      <div className="flex justify-between">
        <NtButton
          label={questionSubmitted ? "Thank you" : "Submit Question"}
          onClick={onClick}
          loading={loading}
          disabled={questionSubmitted}
        />
        {questionSubmitted ? (
          <div className="pt-2 text-center text-npt_colors-350">
            Thank you for submitting your question. We will respond in a timely
            manner.
          </div>
        ) : (
          <></>
        )}
      </div>
      <div className="flex justify-center">
        {hasApiError ? <ApiError /> : <></>}
      </div>
    </div>
  );
}

export default FrequentlyAskedQuestions;

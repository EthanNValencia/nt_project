import React, { useState, useEffect } from "react";
import NtButton from "../components/NtButton";
import { postFaq, getAnsweredFAQs } from "../axios/api";
import ApiError from "../components/ApiError";

function FrequentlyAskedQuestion(props) {
  const { faq } = props;

  return (
    <div className="grid grid-flow-row grid-cols-2 border-b-2 border-egi-50">
      <p>{faq.question}</p>
      <p>{faq.answer}</p>
    </div>
  );
}

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
        setFAQs(data);
        setHasApiError(false);
      } catch (error) {
        // console.error("Error loading FAQ:", error);
        setHasApiError(true);
      }
    }
    fetchFaqs();
  }, []);

  return (
    <div className="p-4">
      <div className="grid grid-flow-row grid-cols-2 border-b-4 border-egi-50 text-2xl tracking-tighter">
        <h1>Questions</h1>
        <h1>Answers</h1>
      </div>

      {faqs.map((faq) => (
        <FrequentlyAskedQuestion faq={faq} key={faq.id} />
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
          className="block p-2.5 w-full text-sm placeholder-black text-black bg-egi-70 rounded-lg border border-egi-40 focus:ring-egi-50 focus:border-egi-50"
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

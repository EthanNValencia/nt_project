import React from "react";

function FAQsDiv(props) {
  const { faq } = props;

  /*
  console.log(
    "FAQs faq" +
      faq.id +
      " = new FAQs(); \n" +
      "faq" +
      faq.id +
      '.setQuestion("' +
      faq.question +
      '");\n' +
      "faq" +
      faq.id +
      '.setAnswer("' +
      faq.answer +
      '");\n' +
      "faq" +
      faq.id +
      ".setQuestionIsAnswered(true);"
  );
  */

  return (
    <div className="grid grid-flow-row grid-cols-2 border-b-2">
      <p>{faq.question}</p>
      <p>{faq.answer}</p>
    </div>
  );
}

export default FAQsDiv;

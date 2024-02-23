import React from "react";

const Paragraph = (props) => {
  const { text } = props;
  return <div className="text-green-500 indent-2">{text}</div>;
};

const Quote = (props) => {
  const { text } = props;
  return <div className="italic text-red-500 text-center">{text}</div>;
};

const BulletPoints = (props) => {
  const { text: points, introduction } = props;
  return (
    <div className="flex justify-center w-full">
      <div className="w-11/12">
        <div>
          {introduction ? (
            <div className="text-orange-500 underline">{introduction}</div>
          ) : (
            <></>
          )}
        </div>
        <div className="text-blue-500 flex justify-center">
          <ul className="list-disc grid grid-flow-row grid-cols-2 gap-x-4 tracking-tighter font-light">
            {points.map((point, index) => (
              <BulletPoint point={point} key={index} />
            ))}{" "}
          </ul>
        </div>
      </div>
    </div>
  );
};

const BulletPoint = (props) => {
  const { point } = props;
  return <li className="text-blue-500">{point}</li>;
};

const Text = (props) => {
  const { text, type, introduction } = props.content;

  if (type === TextType.PARAGRAPH) {
    return <Paragraph text={text} />;
  }
  if (type === TextType.QUOTE) {
    return <Quote text={text} />;
  }
  if (type === TextType.BULLETPOINTS) {
    return (
      <div>
        <BulletPoints text={text} introduction={introduction} />
      </div>
    );
  }
  return null;
};

function Article(props) {
  const { article } = props;

  const renderArticle = (article) => {
    return (
      <div className="py-1">
        <div className="shadow-lg p-1 rounded-lg border border-npt_colors-300">
          <div className="text-lg text-center">{article.title}</div>
          {article.content.map((content, index) => (
            <div className="text-sm py-1">
              <Text content={content} key={index} />
            </div>
          ))}
        </div>
      </div>
    );
  };

  return <div>{renderArticle(article)}</div>;
}

export default Article;

const TextType = {
  QUOTE: "QUOTE",
  PARAGRAPH: "PARAGRAPH",
  BULLETPOINTS: "BULLETPOINTS",
};

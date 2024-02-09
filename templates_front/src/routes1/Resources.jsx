import React, { useState } from "react";
import Article from "../components/Article";

const Resources = () => {
  const [hasApiError, setHasApiError] = useState(false);

  return (
    <div>
      <div className="flex justify-center w-full">
        <div className="w-2/4">
          {ArticlesData.map((article) => (
            <Article key={article.id} article={article} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Resources;

const TextType = {
  QUOTE: "QUOTE",
  PARAGRAPH: "PARAGRAPH",
  BULLETPOINTS: "BULLETPOINTS",
};

const ArticlesData = [
  {
    id: 1,
    title: "Pelvic Therapy",
    content: [
      {
        text: "Strong AND Flexible. Not only is it the basis for Pelvic floor health, but it’s also a great description of Caroline. And as a wife, mom, runner, lifter and pelvic floor PT; she IS the perfect fit to help YOU find healing.",
        type: TextType.PARAGRAPH,
      },
      {
        text: "Stay at home moms, Runners, Athletes, Bookworms, Recently had a baby, Had a baby 20 years ago, or never had a baby. It doesn’t matter who you are, Pelvic Floor Physical Therapy can change your life.",
        type: TextType.PARAGRAPH,
      },
      {
        text: "Just because it’s common, doesn’t make it normal. – Caroline Packard, DPT",
        type: TextType.QUOTE,
      },
      {
        text: [
          "Pelvic Pain",
          "Bladder Leaking",
          "Diastasis Recti",
          "Bladder Prolapse",
          "C-Sections and Scar Restrictions",
          "Constipation",
          "Pregnancy Pains",
          "Post-Partum",
          "and more",
        ],
        type: TextType.BULLETPOINTS,
      },
      {
        text: " Caroline is a proud mom to three kids, a fitness enthusiast, and is extremely passionate about helping women.  Caroline’s past experience as a patient in Pelvic Floor Physical Therapy helped guide her into becoming a professional in the pelvic health field.",
        type: TextType.PARAGRAPH,
      },
      {
        text: "After treating and talking with lots of women the most common feedback she receives is, “Why didn’t someone tell me about this sooner!!”   She believes giving women (even) basic information, such as what they should be doing during pregnancy, labor, post partum and into their “matured” years, could potentially change a lot of outcomes for the overall health of their body.",
        type: TextType.PARAGRAPH,
      },
      {
        text: "Her goal is to educate women about pelvic health, general movement and wellness so they can have a strong healthy body!  She believes  pain is a signal from your body that something is out of balance and encourages women to be an advocate for their health. Caroline is looking forward to helping you find your solution to have a strong and healthy body!",
        type: TextType.PARAGRAPH,
      },
      {
        text: "Follow Caroline on IG. You’ll learn a lot! @carolinepackarddpt",
        type: TextType.PARAGRAPH,
      },
    ],
  },
  {
    id: 2,
    title: "Vestibular Rehab",
    content: [
      {
        text: "There are some kinds of dizzy we encourage…being dizzy with joy, dizzy with laughter, dizzy with excitement – you get the idea. But we also recognize that dizziness and/or loss of balance in real life is no laughing matter. That’s why we’re thrilled to offer our Vestibular Rehab program, aimed at treating individuals who suffer from acute or chronic vestibular and/or balance dysfunctions. These are symptoms often related to issues like:",
        type: TextType.PARAGRAPH,
      },
      {
        text: [
          "Benign Paroxysmal Positional Vertigo",
          "Labarynthitis",
          "Vestibular Neuritis",
          "Meniere’s Syndrome",
          "Perilymph Fistula",
          "or any other disease process that affects the vestibular system",
        ],
        type: TextType.BULLETPOINTS,
      },
      {
        introduction: "Food for thought:",
        text: [
          "5 – 10% of all physician visits are due to dizziness and balance problems.",
          "40% of people over 40 years old visit the doctor due to dizziness.",
          "Dizziness and balances issues are the #1 reason for physician visits in men and women over 45 years of age.",
        ],
        type: TextType.BULLETPOINTS,
      },
      {
        text: "Commonly, a patient who can benefit from Vestibular Rehab will complain of symptoms like dizziness and disequilibrium, which may secondarily cause headaches, de-conditioning and/or muscle tension.",
        type: TextType.PARAGRAPH,
      },
      {
        introduction: "It is our goal to help in the following ways:",
        text: [
          "By decreasing feelings of vertigo and/or dizziness.",
          "By improving balance and posture control.",
          "By improving gaze stability.",
          "By improving overall endurance, depending on the your circumstances.",
        ],
        type: TextType.BULLETPOINTS,
      },
      {
        text: "Let us encourage you to talk to your family physician, Neurologist, or Otolaryngologist (ENT) for a referral to physical therapy today. It’s our privilege to put stability and balance back into your life where it belongs!",
        type: TextType.PARAGRAPH,
      },
    ],
  },
];

/*

Let us encourage you to talk to your family physician, Neurologist, or Otolaryngologist (ENT) for a referral to physical therapy today. It’s our privilege to put stability and balance back into your life where it belongs!

*/

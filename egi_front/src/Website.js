export const BgColors = {
  gold: "bg-egi-10",
  lime: "bg-egi-20",
  green: "bg-egi-30",
  blue: "bg-egi-40",
  purple: "bg-egi-50",
};

export const TextColors = {
  gold: "text-egi-10",
  lime: "text-egi-20",
  green: "text-egi-30",
  blue: "text-egi-40",
  purple: "text-egi-50",
  darkBlue: "text-egi-60",
  lightGold: "text-egi-70",
};

export const Fonts = {
  primaryFont: "font-chewy",
  secondaryFont: "font-montserrat",
};

export const WebsiteColors = {
  buttonColors: "bg-egi-40 hover:bg-egi-20 text-white hover:text-black",
  disabledButtonColors: "text-black bg-slate-600",
  hoverLight: "hover:" + TextColors.green,
  homeTextColor: TextColors.darkBlue,
};

export const HeaderData = {
  bgColor: BgColors.gold,
  businessNameFont: Fonts.primaryFont,
  sloganFont: Fonts.secondaryFont,
  businessName: "EcoGlow Innovations",
  slogan: "Illuminate Your World, Sustainably",
  businessNameSizes: "xl:text-xl lg:text-md md:text-xs sm:text-sm",
  sloganSizes: "xl:text-xl lg:text-sm md:text-xs sm:text-xs",
  textColor: TextColors.blue,
};

export const FooterData = {
  bgColor: BgColors.gold,
  textColor: TextColors.blue,
  primaryFont: Fonts.primaryFont,
  secondaryFont: Fonts.secondaryFont,
  iconSizes: "w-9 h-9",
  iconColors: "hover:text-egi-20 text-egi-40",
};

export const FAQCategory = {
  solarglow: "SolarGlow Lamps",
  windbrite: "WindBrite Lanterns",
  hydrobeam: "HydroBeam Desk Lamps",
  bambooglow: "BambooGlo Pendants",
};

export const FAQs = [
  {
    id: 1,
    category: FAQCategory.solarglow,
    question: "How do SolarGlow Lamps work?",
    answer:
      "SolarGlow Lamps harness solar energy through photovoltaic cells, converting sunlight into electricity to power LED lights.",
  },
  {
    id: 2,
    category: FAQCategory.solarglow,
    question: "Can SolarGlow Lamps be used indoors?",
    answer:
      "SolarGlow Lamps are designed for outdoor use, as they rely on sunlight for power.",
  },
  {
    id: 3,
    category: FAQCategory.solarglow,
    question:
      "What is the lifespan of the solar panels used in SolarGlow Lamps?",
    answer:
      "The solar panels have a long lifespan and are designed to endure various weather conditions.",
  },
  {
    id: 4,
    category: FAQCategory.solarglow,
    question: "How long do SolarGlow Lamps stay illuminated at night?",
    answer:
      "The illumination duration depends on the amount of sunlight received during the day; on a full charge, they typically provide several hours of light.",
  },
  {
    id: 5,
    category: FAQCategory.windbrite,
    question: "How much wind is required to power WindBrite Lanterns?",
    answer:
      "WindBrite Lanterns are designed to generate energy with even a gentle breeze, and the built-in wind turbine is efficient in various wind conditions.",
  },
  {
    id: 6,
    category: FAQCategory.windbrite,
    question: "Are WindBrite Lanterns suitable for all outdoor spaces?",
    answer:
      "Yes, WindBrite Lanterns are versatile and suitable for gardens, patios, balconies, and other outdoor spaces.",
  },
  {
    id: 7,
    category: FAQCategory.windbrite,
    question:
      "Do WindBrite Lanterns store excess energy for use during calm periods?",
    answer:
      "WindBrite Lanterns are designed to provide continuous illumination and do not typically include energy storage features.",
  },
  {
    id: 8,
    category: FAQCategory.hydrobeam,
    question:
      "How does the water-based energy system in HydroBeam Desk Lamps work?",
    answer:
      "The lamps use a water-based energy system to generate power, converting water flow into energy that powers the LED lights.",
  },
  {
    id: 9,
    category: FAQCategory.hydrobeam,
    question: "Can HydroBeam Desk Lamps be used with any type of water?",
    answer:
      "The lamps are designed to work with a variety of water types, including tap water, and do not require any special water sources.",
  },
  {
    id: 10,
    category: FAQCategory.hydrobeam,
    question:
      "Are HydroBeam Desk Lamps adjustable for different lighting preferences?",
    answer:
      "Yes, HydroBeam Desk Lamps feature adjustable brightness levels to suit various work or study environments.",
  },
  {
    id: 11,
    category: FAQCategory.bambooglow,
    question: "Is the bamboo used in BambooGlo Pendants sustainably sourced?",
    answer:
      "Yes, we are committed to using sustainably sourced bamboo in the crafting of BambooGlo Pendants.",
  },
  {
    id: 12,
    category: FAQCategory.bambooglow,
    question: "Can BambooGlo Pendants be used in damp or humid environments?",
    answer:
      "While the pendants are durable, it is recommended to avoid prolonged exposure to excessive moisture.",
  },
  {
    id: 13,
    category: FAQCategory.bambooglow,
    question: "Do BambooGlo Pendants come with energy-efficient LED bulbs?",
    answer:
      "Yes, BambooGlo Pendants come equipped with energy-efficient LED bulbs, contributing to a more eco-conscious lifestyle.",
  },
];

// https://unsplash.com/s/photos/fake-ceo

export const Executives = [
  {
    id: 1,
    firstName: "Emily",
    lastName: "Greenway",
    position: "CEO",
    title: "",
    credentials: "",
    image:
      "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    bio: "Emily Greenway, the visionary leader behind EcoGlow Innovations, is a seasoned entrepreneur with a passion for sustainability and innovation. With a background in environmental science and a track record of successful eco-conscious ventures, Emily founded EcoGlow to make a significant impact on the lighting industry. Her unwavering commitment to a greener future, coupled with her strategic business acumen, has driven the company to the forefront of sustainable lighting solutions. Emily envisions a world where cutting-edge technology and environmental responsibility coexist harmoniously, and she is dedicated to leading EcoGlow Innovations toward that bright future.",
  },
  {
    id: 2,
    firstName: "Alexander",
    lastName: "Sunlighter",
    position: "CTO",
    title: "Dr.",
    credentials: "PhD",
    image:
      "https://images.unsplash.com/flagged/photo-1553642618-de0381320ff3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGZha2UlMjBjZW98ZW58MHx8MHx8fDA%3D",
    bio: "Dr. Alexander Sunlighter, PhD, serves as the Chief Technology Officer (CTO) at EcoGlow Innovations, bringing a wealth of knowledge and expertise in renewable energy technologies. With a doctorate in Sustainable Engineering, Alexander is the driving force behind the technical innovation that powers EcoGlow's lighting solutions. His groundbreaking work in harnessing solar, wind, and water energy has positioned EcoGlow at the forefront of sustainable design. Alexander's commitment to pushing the boundaries of eco-friendly technology is evident in each product, as he continues to lead the company towards new heights in energy efficiency and environmental responsibility.",
  },
  {
    id: 3,
    firstName: "Olivia",
    lastName: "Lumens",
    position: "CFO",
    title: "",
    credentials: "",
    image:
      "https://images.unsplash.com/photo-1573497019940-1c28c88b4f3e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fGZha2UlMjBjZW98ZW58MHx8MHx8fDA%3D",
    bio: "Olivia Lumens, the Chief Financial Officer (CFO) of EcoGlow Innovations, is a financial strategist with a keen eye for sustainable business practices. Olivia's extensive background in finance and her commitment to corporate responsibility make her an integral part of EcoGlow's leadership team. With a focus on balancing financial growth and environmental impact, she ensures that the company's financial strategies align with its mission. Olivia is dedicated to fostering financial sustainability while maximizing the positive influence EcoGlow has on both its customers and the planet.",
  },
];

const ContactType = {
  office: "Office",
  customerSupport: "Customer Support",
  mediaInquiries: "Media Inquiries",
  salesAndPartnerships: "Sales and Partnerships",
};

export const SocialMediaProfile = {
  linkedin: "",
  youtube: "https://www.youtube.com/",
  facebook: "https://www.facebook.com/",
  myspace: "",
  instagram: "https://www.instagram.com/",
  yelp: "https://www.yelp.com/",
  tiktok: "https://www.tiktok.com/",
  x: "https://twitter.com/",
  pinterest: "https://www.pinterest.com/",
  snapchat: "",
  whatsapp: "https://www.whatsapp.com/",
  tumblr: "",
  google: "",
};

export const ContactInformation = {
  contactType: ContactType,
  contactInformation: [
    {
      type: ContactType.office,
      street: "123 EcoWay",
      unit: null,
      city: "Sustainable City",
      state: "Greenland",
      zip: null,
      acceptingWalkIns: true,
      mapUrl: null,
      introduction:
        "Welcome to the heart of innovation at EcoGlow Innovations' Corporate Headquarters. Nestled in the heart of Sustainable City in Greenland, our state-of-the-art facility at 123 EcoWay is more than an office—it's a beacon of sustainability. Here, we channel our commitment to eco-friendly lighting solutions, designing a brighter and greener future. Our headquarters is not just a place of work; it's a manifestation of our mission to illuminate lives while minimizing our impact on the planet. Feel the pulse of innovation at EcoGlow Innovations, where every corner is infused with the spirit of a more environmentally conscious world.",
      phone: "+1 (555) 123-4567",
      fax: null,
      email: "info@ecoglowinnovations.com",
      schedule: [
        {
          id: 2,
          day: "Wednesday",
          beginTime: "07:00:00",
          endTime: "18:30:00",
          daysArr: null,
        },
        {
          id: 3,
          day: "Tuesday",
          beginTime: "07:00:00",
          endTime: "18:30:00",
          daysArr: null,
        },
        {
          id: 1,
          day: "Thursday",
          beginTime: "07:00:00",
          endTime: "18:30:00",
          daysArr: null,
        },
        {
          id: 4,
          day: "Friday",
          beginTime: "13:00:00",
          endTime: "15:00:00",
          daysArr: null,
        },
        {
          id: 5,
          day: "Monday",
          beginTime: "07:00:00",
          endTime: "18:30:00",
          daysArr: null,
        },
      ],
    },
    {
      type: ContactType.customerSupport,
      contact: null,
      phone: "+1 (555) 789-0123",
      email: "support@ecoglowinnovations.com",
    },
    {
      type: ContactType.mediaInquiries,
      contact: "Amanda Greenfield",
      phone: "+1 (555) 987-6543",
      email: "media@ecoglowinnovations.com",
    },
    {
      type: ContactType.salesAndPartnerships,
      contact: "Oliver Lumens",
      phone: "+1 (555) 234-5678",
      email: "sales@ecoglowinnovations.com",
    },
  ],
};

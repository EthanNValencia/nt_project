export const WeekDays = {
  Sat: "Sat",
  Mon: "Mon",
  Tue: "Tue",
  Wed: "Wed",
  Thu: "Thu",
  Fri: "Fri",
  Sun: "Sun",
};

export const TextType = {
  PARAGRAPH: "PARAGRAPH",
  QUOTE: "QUOTE",
  IMAGE: "IMAGE",
};

export const ROLES = {
  USER: "USER",
  ADMIN: "ADMIN",
  SUPER: "SUPER",
  ERROR: "ERROR",
};

export const ROLES_ARR = Object.values(ROLES);

// ai, io, .info, .site, .me, .
export const DOMAINS = {
  org: ".org",
  com: ".com",
  net: ".net",
  co: ".co",
  us: ".us",
  ai: ".ai",
  io: ".io",
  info: ".info",
  site: ".site",
  me: ".me",
};

export const DOMAINS_ARR = Object.values(DOMAINS);

export const FONT_FAMILY = {
  sans: "font-sans",
  serif: "font-serif",
  mono: "font-mono",
};

export const FONT_FAMILY_ARR = Object.values(FONT_FAMILY);

export const Profile = {
  linkedin: "",
  youtube: "",
  facebook: "",
  myspace: "",
  instagram: "",
  yelp: "",
  tiktok: "",
  x: "",
  pinterest: "",
  snapchat: "",
  whatsapp: "",
  tumblr: "",
  google: "",
  id: 1,
};

export const BiographicalText = {
  id: null,
  type: TextType.PARAGRAPH,
  position: null,
  text: "",
};

export const InformationalText = {
  id: null,
  type: TextType.PARAGRAPH,
  position: null,
  text: "",
};

export const NewSchedule = [
  { id: null, day: WeekDays.Sat, beginTime: "00:00:00", endTime: "00:00:00" },
  { id: null, day: WeekDays.Mon, beginTime: "07:00:00", endTime: "18:30:00" },
  { id: null, day: WeekDays.Wed, beginTime: "07:00:00", endTime: "18:30:00" },
  { id: null, day: WeekDays.Thu, beginTime: "07:00:00", endTime: "18:30:00" },
  { id: null, day: WeekDays.Fri, beginTime: "13:00:00", endTime: "15:00:00" },
  { id: null, day: WeekDays.Tue, beginTime: "07:00:00", endTime: "18:30:00" },
  { id: null, day: WeekDays.Sun, beginTime: "00:00:00", endTime: "00:00:00" },
];

export const NewOffice = {
  officeId: null,
  street: "",
  unit: "",
  city: "",
  state: "",
  zip: "",
  phone: "",
  fax: "",
  email: "",
  acceptingWalkIns: false,
  mapUrl: "",
  introduction: "",
  officeSocialMedialProfile: Profile,
  schedule: NewSchedule,
  employees: [],
  statesArr: [{ MI: "MI" }],
};

export const NewEmployee = {
  id: null,
  firstName: "",
  middleName: "",
  lastName: "",
  img: "",
  role_id: "",
  role: "",
  meta: "",
  email: "",
  workPhone: "",
  personalPhone: "",
  subject: "",
  possessive: "",
  services: [],
  appointments: [],
  schedule: NewSchedule,
  office: NewOffice,
  employeeContent: null,
  profile: Profile,
  biographicalTexts: [],
  informationalTexts: [],
};

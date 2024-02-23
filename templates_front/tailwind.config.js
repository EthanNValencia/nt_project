/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}",
    "./src/pages/**/*.{js,jsx,ts,tsx}",
    "./src/components/**/*.{js,jsx,ts,tsx}",
    "./src/containers/**/*.{js,jsx,ts,tsx}",
    "./node_modules/tw-elements/dist/js/**/*.js",
  ],
  theme: {
    extend: {
      colors: {
        btn_light: {
          text: "#FFFFFF",
          hover_text: "#000000",
          bg: "#256c70",
          hover: "#ffe97d",
        },
        btn_dark: {
          1: "",
          2: "",
          3: "",
        },
        vuejs: {
          100: "#49e659",
          932: "#434252",
        },
        npt_colors: {
          1: "#313b48",
          2: "#28313d",
          10: "#ffe97d",
          20: "#d1d1e1",
          30: "#c0ebf1",
          300: "#30afb8",
          325: "#537173",
          350: "#256c70",
        },
        nss: {
          1: "#1C1A1A",
          10: "#000",
          20: "#d3d3d3",
          21: "#A9A9A9",
          300: "#2f0909",
          305: "#8C4646",
        },
        egi: {
          10: "#BFAE48",
          20: "#5FAD41",
          30: "#2D936C",
          40: "#391463",
          50: "#3A0842",
          60: "#331e6d",
          70: "#ffcf31",
        },
      },
      fontFamily: {
        lato: ["Lato"],
        chewy: ["Chewy"],
        montserrat: ["Montserrat"],
      },
    },
  },
  darkMode: "class",
  plugins: [require("tw-elements/dist/plugin.cjs")],
};

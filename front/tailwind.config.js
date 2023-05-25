/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./layout/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        mainColor: "#1F4DED",
        mainUpColor: "#0034EB",
        mainDownColor: "#496CE7",
        borderColor: "rgb(228, 228, 235)",
        textGrayColor: "#72717d",
        redColor: "#D8494A",
      },
      transitionProperty: {},
      keyframes: {
        slideFadeInDropdownAnimation: {
          "0%": {
            transform: "translateY(-100%)",
          },
          "100%": {
            transform: "translateY(0)",
          },
        },
        slideFadeOutDropdownAnimation: {
          "0%": {
            transform: "translateY(0)",
          },
          "100%": {
            transform: "translateY(-100%)",
          },
        },
        dropDown: {
          "0%": { transform: "translateY(-100%) " },
          "100%": { transform: "translateY(0) " },
        },
      },
      animation: {
        dropDown: "dropDown .2s ease",
      },
    },
  },
  plugins: [],
};

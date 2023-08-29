interface headerNavListType {
  className: string;
  link: string;
  navName: string;
}
export const headerNavList: Array<headerNavListType> = [
  {
    className: "Register-Rebate",
    link: "/registration",
    navName: "Register Payback",
  },
  {
    className: "Exchange-Fee-Calculator",
    link: "/calculator",
    navName: "Rebation Calculator",
  },
  {
    className: "How-It-Works",
    link: "/introduction",
    navName: "How It Works",
  },
  {
    className: "Crpyto-Exchange",
    link: "/exchange",
    navName: "Crypto Exchange",
  },
  {
    className: "Crpyto-Screener",
    link: "/screener",
    navName: "Screener",
  },
  {
    className: "Event",
    link: "/event",
    navName: "Event",
  },
];

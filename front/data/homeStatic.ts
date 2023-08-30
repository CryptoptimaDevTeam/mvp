interface questionDropdownDataType {
  order: number;
  title: string;
  body: string;
}

export const questionDropdownData: Array<questionDropdownDataType> = [
  {
    order: 1,
    title:
      "Can I register if I already have an account on the crypto exchange?",
    body: "adklawopdjaopwdiawjdioajwdoiawdj",
  },
];

interface exchangeFeeDataType {
  exchangeName: string;
  exchangeImgUrl: string;
  normalSpotLimit: number;
  normalSpotMarket: number;
  referralSpotLimit: number;
  referralSpotMarket: number;
  cryptoptimaSpotLimit: number;
  cryptoptimaSpotMarket: number;
  normalDerivativesLimit: number;
  normalDerivativesMarket: number;
  referralDerivativesLimit: number;
  referralDerivativesMarket: number;
  cryptoptimaDerivativesLimit: number;
  cryptoptimaDerivativesMarket: number;
  spotExpiration: number | "infinity";
  futuresExpiration: number | "infinity";
  bonus: number;
}

export const exchangeFeeData: Array<exchangeFeeDataType> = [
  {
    exchangeName: "Binance",
    exchangeImgUrl: "/image/logo/logo_binance_small.svg",
    normalSpotLimit: 0.1,
    normalSpotMarket: 0.1,
    referralSpotLimit: 0.09,
    referralSpotMarket: 0.09,
    cryptoptimaSpotLimit: 0.063,
    cryptoptimaSpotMarket: 0.063,
    normalDerivativesLimit: 0.02,
    normalDerivativesMarket: 0.04,
    referralDerivativesLimit: 0.018,
    referralDerivativesMarket: 0.036,
    cryptoptimaDerivativesLimit: 0.0126,
    cryptoptimaDerivativesMarket: 0.0252,
    spotExpiration: 0,
    futuresExpiration: 0,
    bonus: 500,
  },
  {
    exchangeName: "Bybit",
    exchangeImgUrl: "/image/logo/logo_bybit_small.svg",
    normalSpotLimit: 0.1,
    normalSpotMarket: 0.1,
    referralSpotLimit: 0.08,
    referralSpotMarket: 0.08,
    cryptoptimaSpotLimit: 0.0584,
    cryptoptimaSpotMarket: 0.0584,
    normalDerivativesLimit: 0.01,
    normalDerivativesMarket: 0.06,
    referralDerivativesLimit: 0.01,
    referralDerivativesMarket: 0.048,
    cryptoptimaDerivativesLimit: 0.0073,
    cryptoptimaDerivativesMarket: 0.0353,
    spotExpiration: 0,
    futuresExpiration: 0,
    bonus: 3000,
  },
  {
    exchangeName: "OKX",
    exchangeImgUrl: "/image/logo/logo_okx_small.svg",
    normalSpotLimit: 0.08,
    normalSpotMarket: 0.1,
    referralSpotLimit: 0.056,
    referralSpotMarket: 0.07,
    cryptoptimaSpotLimit: 0.0409,
    cryptoptimaSpotMarket: 0.0511,
    normalDerivativesLimit: 0.02,
    normalDerivativesMarket: 0.05,
    referralDerivativesLimit: 0.014,
    referralDerivativesMarket: 0.035,
    cryptoptimaDerivativesLimit: 0.0102,
    cryptoptimaDerivativesMarket: 0.0256,
    spotExpiration: 0,
    futuresExpiration: 0,
    bonus: 0,
  },
  {
    exchangeName: "Bitget",
    exchangeImgUrl: "/image/logo/logo_bitget_small.svg",
    normalSpotLimit: 0.1,
    normalSpotMarket: 0.1,
    referralSpotLimit: 0.05,
    referralSpotMarket: 0.05,
    cryptoptimaSpotLimit: 0.04,
    cryptoptimaSpotMarket: 0.04,
    normalDerivativesLimit: 0.02,
    normalDerivativesMarket: 0.06,
    referralDerivativesLimit: 0.02,
    referralDerivativesMarket: 0.04,
    cryptoptimaDerivativesLimit: 0.009,
    cryptoptimaDerivativesMarket: 0.018,
    spotExpiration: 0,
    futuresExpiration: 0,
    bonus: 5000,
  },
];

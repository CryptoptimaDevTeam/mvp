import type { CarouselListType } from "../components/atoms/carousel";
import { styled } from "styled-components";

interface CarouselFramePropsType {
  cursor?: string;
}

const CarouselFrame = styled.div<CarouselFramePropsType>`
  height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
  cursor: ${(props) => props.cursor || "default"};
`;

const CarouselWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const CarouselText = styled.div`
  text-align: center;
  font-size: 36px;
  font-weight: bold;
`;

const CarouselDate = styled.div`
  padding-top: 40px;
  font-size: 20px;
  font-weight: 600;
  position: absolute;
  top: -80px;
  color: #72717d;
`;

// 슬라이드 관련 데이터
export const carouselList: Array<CarouselListType> = [
  {
    RC: (
      <CarouselFrame>
        <CarouselText>
          Cryptoptima has given back an average of{" "}
          <span className="text-mainColor">$629 per month</span>
          <br />
          in trading fees to{" "}
          <span className="text-mainColor">over 130,000 traders</span>{" "}
          worldwide!
        </CarouselText>
      </CarouselFrame>
    ),
  },
  {
    RC: (
      <CarouselFrame cursor="pointer" onClick={() => {}}>
        <CarouselWrapper>
          <CarouselText>
            New registrants can immediately receive <br />
            either <span className="text-mainColor">50 USDT</span> or a{" "}
            <span className="text-mainColor">cryptocurrency random box</span>
          </CarouselText>
          <CarouselDate>2023.06.01 ~ 2023.06.30</CarouselDate>
        </CarouselWrapper>
      </CarouselFrame>
    ),
  },
  {
    RC: (
      <CarouselFrame cursor="pointer" onClick={() => {}}>
        <CarouselWrapper>
          <CarouselDate>2023.06.12 ~ 2023.12.31</CarouselDate>
          <CarouselText>
            To celebrate Cryptoptima's 1st anniversary,
            <br />
            new registrants can receive{" "}
            <span className="text-mainColor">
              Tradingview subscription support{" "}
            </span>
            for free
          </CarouselText>
        </CarouselWrapper>
      </CarouselFrame>
    ),
  },
];

interface exchangeListType {
  exchangeName: string;
  exchangeImgUrl: string;
  exchangePossibleUserCount: number;
  exchangeUserCount: number;
  exchangeAvgPaybackAmount: number;
  exchangePaybackRate: number;
  exchangeFeeDiscountRate: number;
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
export type { exchangeListType };

// 거래소 관련 데이터
export const exchangeList: Array<exchangeListType> = [
  {
    exchangeName: "binance",
    exchangeImgUrl: "/image/logo/logo_binance_full.svg",
    exchangePossibleUserCount: 50000,
    exchangeUserCount: 50000,
    exchangeAvgPaybackAmount: 548,
    exchangePaybackRate: 90,
    exchangeFeeDiscountRate: 10,
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
    exchangeName: "bybit",
    exchangeImgUrl: "/image/logo/logo_bybit_full.svg",
    exchangePossibleUserCount: 50000,
    exchangeUserCount: 41179,
    exchangeAvgPaybackAmount: 716,
    exchangePaybackRate: 90,
    exchangeFeeDiscountRate: 20,
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
    exchangeName: "okx",
    exchangeImgUrl: "/image/logo/logo_okx_full.svg",
    exchangePossibleUserCount: 40000,
    exchangeUserCount: 33721,
    exchangeAvgPaybackAmount: 637,
    exchangePaybackRate: 90,
    exchangeFeeDiscountRate: 30,
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
    exchangeName: "bitget",
    exchangeImgUrl: "/image/logo/logo_bitget_full.svg",
    exchangePossibleUserCount: 20000,
    exchangeUserCount: 11842,
    exchangeAvgPaybackAmount: 783,
    exchangePaybackRate: 99,
    exchangeFeeDiscountRate: 50,
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

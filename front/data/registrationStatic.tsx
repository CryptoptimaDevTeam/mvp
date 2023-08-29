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
  },
  {
    exchangeName: "bybit",
    exchangeImgUrl: "/image/logo/logo_bybit_full.svg",
    exchangePossibleUserCount: 50000,
    exchangeUserCount: 41179,
    exchangeAvgPaybackAmount: 716,
    exchangePaybackRate: 90,
    exchangeFeeDiscountRate: 20,
  },
  {
    exchangeName: "okx",
    exchangeImgUrl: "/image/logo/logo_okx_full.svg",
    exchangePossibleUserCount: 40000,
    exchangeUserCount: 33721,
    exchangeAvgPaybackAmount: 637,
    exchangePaybackRate: 90,
    exchangeFeeDiscountRate: 30,
  },
  {
    exchangeName: "bitget",
    exchangeImgUrl: "/image/logo/logo_bitget_full.svg",
    exchangePossibleUserCount: 20000,
    exchangeUserCount: 11842,
    exchangeAvgPaybackAmount: 783,
    exchangePaybackRate: 99,
    exchangeFeeDiscountRate: 50,
  },
];

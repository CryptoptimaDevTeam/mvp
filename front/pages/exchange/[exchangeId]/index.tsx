import { useRouter } from "next/router";
import React from "react";

const ExchangeDetail: React.FC = () => {
  const router = useRouter();
  const exchangeId = router.query.exchangeId
    ? +router.query.exchangeId
    : undefined;

  const rgmainSectionStyle: string =
    "max-w-[1248px] mx-auto flex justify-center items-center";
  return (
    <div>
      <section></section>
    </div>
  );
};

export default ExchangeDetail;

import { useRouter } from "next/router";
import React from "react";

const ExchangeDetail: React.FC = () => {
  const router = useRouter();
  const exchangeName = router.query.exchangeName;

  const rgmainSectionStyle: string =
    "max-w-[1248px] mx-auto flex justify-center items-center";
  return (
    <div>
      <section>{exchangeName}</section>
    </div>
  );
};

export default ExchangeDetail;

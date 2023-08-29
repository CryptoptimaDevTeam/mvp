import Image from "next/image";
import { Carousel } from "../../components/atoms/carousel";
import { carouselList, exchangeList } from "../../data/registrationStatic";
import { ExchangeCardVertical } from "../../components/blocks/card";

export default function RegistrationMain() {
  const rgmainSectionStyle: string =
    "max-w-[1248px] mx-auto flex justify-center items-center";
  return (
    <div>
      <section
        className={`rgmain-section1 pb-[100px] flex justify-center items-center w-full`}
      >
        <Carousel carouselList={carouselList} />
      </section>
      <section className={`rgmain-section2 ${rgmainSectionStyle}`}>
        <div>
          <ul className="flex gap-10">
            {exchangeList.map((el) => (
              <li key={el.exchangeName}>
                <ExchangeCardVertical {...el} />
              </li>
            ))}
          </ul>
        </div>
      </section>
      <section className={`rgmain-section3 ${rgmainSectionStyle}`}></section>
    </div>
  );
}

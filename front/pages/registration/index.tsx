import { Carousel } from "../../components/atoms/carousel";
import { carouselList } from "../../data/registrationStatic";

export default function RegistrationMain() {
  const rgmainSectionStyle: string =
    "max-w-[1248px] mx-auto py-[100px] flex justify-center items-center";
  return (
    <div>
      <section
        className={`rgmain-section1 pb-[100px] flex justify-center items-center w-full`}
      >
        <Carousel carouselList={carouselList} />
      </section>
      <section className={`rgmain-section2 ${rgmainSectionStyle}`}></section>
      <section className={`rgmain-section3 ${rgmainSectionStyle}`}></section>
    </div>
  );
}

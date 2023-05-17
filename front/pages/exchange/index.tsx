import { exchangeList } from "../../data/registrationStatic";
import { ExchangeCardHorizontal } from "../../components/blocks/card";

export default function ExchangeMain() {
  const rgmainSectionStyle: string =
    "max-w-[1248px] mx-auto flex justify-center items-center";
  return (
    <div className="py-[100px]">
      <section className={`rgmain-section2 ${rgmainSectionStyle}`}>
        <div>
          <ul className="flex flex-col gap-10">
            {exchangeList.map((el) => (
              <li key={el.exchangeName}>
                <ExchangeCardHorizontal {...el} />
              </li>
            ))}
          </ul>
        </div>
      </section>
      <section></section>
    </div>
  );
}

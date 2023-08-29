import Image from "next/image";
import Link from "next/link";
import { BsArrowLeft } from "react-icons/bs";
import { useRouter } from "next/router";

interface RegistrationHeaderPropsType {
  progressStatus: number;
  setProgressStatus: React.Dispatch<React.SetStateAction<number>>;
  progressChange: number;
}

export const RegistrationHeader = ({
  progressStatus,
  setProgressStatus,
  progressChange,
}: RegistrationHeaderPropsType) => {
  const router = useRouter();

  return (
    <div className="header-componentainer sticky top-0 z-50">
      <header className="header-wrapper flex h-[70px] px-6 justify-between items-center shadow-[0_5px_20px_0_rgba(11,7,110,.04)] bg-white">
        <div className="header-left flex justify-start items-center flex-[1_1_0%]">
          <div
            className={`${
              progressStatus === progressChange ? "invisible" : ""
            } go-back-btn px-[15px] py-[5px] rounded-[10px] transition-all duration-300 hover:bg-[#f5f5f5] cursor-pointer`}
            onClick={() => {
              setProgressStatus((status) => status - progressChange);
            }}
          >
            <BsArrowLeft size={24} />
          </div>
        </div>
        <div className="header-middle flex justify-center items-center gap-10 flex-[1_1_0%]">
          <div className="logo-area">
            <Link href="/">
              <div className="logo-image w-[150px]">
                <Image
                  className="logo"
                  src="/image/logo/logo_full_l.svg"
                  alt="logo"
                  width="1613"
                  height="363"
                />
              </div>
            </Link>
          </div>
        </div>
        <div className="header-right flex justify-end gap-5 flex-[1_1_0%]"></div>
      </header>
    </div>
  );
};

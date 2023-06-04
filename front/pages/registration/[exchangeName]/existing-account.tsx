import type { ReactElement } from "react";
import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import { useForm, SubmitHandler } from "react-hook-form";
import NoLayout from "../../../layout/noLayout";
import { RegistrationHeader } from "../../../components/blocks/registrationHeader";
import { ProgressBar } from "../../../components/atoms/progressBar";
import { MainButton, SubButton } from "../../../components/atoms/button";
import { Alert } from "../../../components/atoms/alert";
import { Modal } from "../../../components/atoms/modal";
import Image from "next/image";
import ReactPlayer from "react-player";

interface FormValeType {
  uid: string;
}

type DescriptionType = "video" | "text" | "";
type ModalType = "uidRegistrationCheck" | "uidGuideVideo" | "";

const RegistrationExistingAccount = () => {
  const router = useRouter();
  const exchangeName =
    typeof router.query.exchangeName === "string"
      ? router.query.exchangeName
      : "";
  const progressChange: number = (1 / 4) * 100;
  const [progressStatus, setProgressStatus] = useState<number>(progressChange);
  const [descriptionType, setDescriptionType] = useState<DescriptionType>("");
  const [userUid, setUserUid] = useState<string>("");
  const { register, handleSubmit, reset } = useForm<FormValeType>();
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const [modalType, setModalType] = useState<ModalType>("");

  useEffect(() => {
    setModalType("");
    setUserUid("");
  }, [progressStatus]);

  const registerUidHandle: SubmitHandler<FormValeType> = (data) => {
    if (data.uid.length === 0) {
      setAlertStatus({
        isOpen: true,
        message: "Enter your UID!",
        type: "caution",
        top: "top-[90px]",
      });
    } else {
      setUserUid(data.uid);
      setModalType("uidRegistrationCheck");
      setIsModalOpen(true);
      reset();
    }
  };

  const uidRegistrationHandle = () => {
    // Backend로 사용자 ui를 보내는 코드가 필요함
  };

  const [alertStatus, setAlertStatus] = useState({
    isOpen: false,
    message: "",
    type: "caution",
    top: "",
  });

  const [windowWidth, setWindowWidth] = useState(0);
  const videoAspectRatio = 1.7778; // 가로-세로 비율

  useEffect(() => {
    // 화면 크기 변화 감지하여 가로 길이 업데이트
    const handleResize = () => {
      setWindowWidth(Math.min(window.innerWidth, 860));
    };
    window.addEventListener("resize", handleResize);
    handleResize(); // 컴포넌트 마운트 시 초기화
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const videoHeight = windowWidth / videoAspectRatio;

  const registrationSectionStyle: string =
    "max-w-[1248px] mx-auto py-[100px] flex justify-center items-center";

  return (
    <div>
      <Alert alertStatus={alertStatus} setAlertStatus={setAlertStatus} />
      <RegistrationHeader
        progressStatus={progressStatus}
        setProgressStatus={setProgressStatus}
        progressChange={progressChange}
      />
      <div className="progress-bar-wrapper sticky top-[70px] z-50">
        <ProgressBar progressStatus={progressStatus} />
      </div>
      {progressStatus === (1 / 4) * 100 ? (
        <section
          className={`registration-no-account-section1 ${registrationSectionStyle} flex-col pt-[60px]`}
        >
          <div className="section-title text-[48px] font-bold text-center max-w-[1248px] px-6">
            Choose a method of progress for registartion
          </div>
          <div className="section-body mt-8 text-[20px] max-w-[1024px] text-[#72717d] text-center">
            You can register for Payback even if you already have a
            cryptocurrency exchange account! Select your preferred method of
            progress: watching videos or reading text. It typically takes less
            than 3 minutes to complete
          </div>
          <div className="progress-select-btn-wrapper mt-20 flex gap-20">
            <div
              className="select-video-btn cursor-pointer"
              onClick={() => {
                setProgressStatus((status) => status + progressChange);
                setDescriptionType("video");
              }}
            >
              <Image
                className="transition-all hover:scale-[1.03]"
                src="/image/icon/icon_video.svg"
                alt="select video"
                width={150}
                height={150}
              />
            </div>
            <div
              className="select-text-btn cursor-pointer"
              onClick={() => {
                setProgressStatus((status) => status + progressChange);
                setDescriptionType("text");
              }}
            >
              <Image
                className="transition-all hover:scale-[1.03]"
                src="/image/icon/icon_text.svg"
                alt="select text"
                width={150}
                height={150}
              />
            </div>
          </div>
        </section>
      ) : progressStatus === (2 / 4) * 100 ? (
        <section
          className={`registration-no-account-section2 ${registrationSectionStyle} flex-col pt-[60px] pb-0`}
        >
          {descriptionType === "video" ? (
            <div className="flex flex-col justify-center items-center gap-10">
              <div className="section-title text-[48px] font-bold text-center">
                Follow the instructions in the video
              </div>
              <div className="rounded-xl overflow-hidden">
                <ReactPlayer
                  url="https://www.youtube.com/watch?v=Mxf4eLZHov0"
                  controls
                  width={`${windowWidth}px`}
                  height={`${videoHeight}px`}
                />
              </div>
              <div className="w-full">
                <MainButton
                  className="next-btn float-right"
                  name="Continue →"
                  onClick={() => {
                    setProgressStatus((status) => status + progressChange);
                  }}
                  width="w-[180px]"
                  hoverBg={true}
                  hoverScale={true}
                  style="py-[15px] text-[20px]"
                />
              </div>
            </div>
          ) : descriptionType === "text" ? (
            <div>
              <div className="section-title text-[48px] font-bold text-center max-w-[960px] px-6">
                Follow the instructions below
              </div>
              <div>이미지들</div>
              <div className="w-full">
                <MainButton
                  className="next-btn float-right"
                  name="Continue →"
                  onClick={() => {
                    setProgressStatus((status) => status + progressChange);
                  }}
                  width="w-[180px]"
                  hoverBg={true}
                  hoverScale={true}
                  style="py-[15px] text-[20px]"
                />
              </div>
            </div>
          ) : null}
        </section>
      ) : progressStatus === (3 / 4) * 100 ? (
        <section
          className={`registration-no-account-section3 ${registrationSectionStyle} flex-col pt-[60px]`}
        >
          <div className="section-title text-[48px] font-bold text-center max-w-[960px] px-6">
            Enter the UID for your new account
          </div>
          <div className="uid-input-wrapper mt-10">
            <form
              className="flex justify-center items-center gap-2.5"
              onSubmit={handleSubmit(registerUidHandle)}
            >
              <input
                className="border-[1px] border-borderColor rounded-lg w-[450px] h-[56px] px-2.5 py-2.5 :focus:outline-mainColor"
                placeholder="Please enter your cryptocurrency exchange UID"
                type="text"
                {...register("uid")}
              />
              <div className="register-btn">
                <MainButton
                  name="Register UID"
                  type="submit"
                  onClick={() => {}}
                  style="py-[16px] px-[19px] w-[250px]"
                  hoverScale={true}
                  hoverBg={true}
                />
              </div>
            </form>
          </div>
          <div className="section-body mt-10 max-w-[886px] text-[#7d7d7d] text-center">
            Do you not know where to find your cryptocurrency exchange UID?{" "}
            <span
              className="text-mainColor font-semibold cursor-pointer px-2"
              onClick={() => {
                setIsModalOpen(true);
                setModalType("uidGuideVideo");
              }}
            >
              Click!
            </span>
          </div>
        </section>
      ) : (
        <section
          className={`registration-no-account-section4 ${registrationSectionStyle} flex-col pt-[60px]`}
        >
          <div className="section-title text-[48px] font-bold text-center max-w-[960px] px-6">
            Payback registration request completed!
          </div>
          <div className="section-body mt-8 text-[20px] max-w-[886px] text-[#72717d] text-center">
            It may take{" "}
            <span className="text-mainColor font-semibold">
              1-3 business days until the registration is completed
            </span>
            .
            <br />
            We will notify you of the registration status through our website
            notification!
          </div>
          <div className="completed-btn-wrapper mt-20 flex justify-center items-center gap-5">
            <MainButton
              name="Back to Main Page"
              onClick={() => {
                router.push("/");
              }}
              style="py-[21px] px-[24px]"
              width="w-[220px]"
              hoverScale={true}
              hoverBg={true}
            />
            <SubButton
              name="Go to Trading"
              onClick={() => {
                window.open("http://www.naver.com", "_blank");
                router.push("/");
              }}
              style="py-[21px] px-[24px]"
              width="w-[220px]"
              hoverScale={true}
            />
          </div>
        </section>
      )}

      <Modal isOpen={isModalOpen} setIsOpen={setIsModalOpen}>
        <div
          className={`${
            modalType === "uidRegistrationCheck" ? "visible" : "invisible"
          }  ${
            isModalOpen ? "opacity-100	" : "opacity-0"
          } uid-registration-check-modal flex flex-col gap-7 justify-center items-center`}
        >
          <div className="uid-registration-check-modal-comment text-xl text-center font-semibold">
            Is the one below your {exchangeName.toUpperCase()} account UID?
          </div>
          <div className="uid-registration-check-modal-uid text-2xl text-mainColor font-semibold text-center">
            {userUid || "Cryptoptima"}
          </div>
          <div className="uid-registration-check-modal-btn-wrapper flex justify-center items-center gap-2.5">
            <SubButton
              className="re-enter-btn"
              name="Re-enter"
              onClick={() => {
                setIsModalOpen(false);
                setModalType("");
                setUserUid("");
              }}
              width="w-[160px]"
              hoverScale={true}
            />
            <MainButton
              className="next-btn"
              name="Right!"
              onClick={() => {
                setIsModalOpen(false);
                setModalType("");
                setProgressStatus((status) => status + progressChange);
                uidRegistrationHandle();
                setUserUid("");
              }}
              width="w-[160px]"
              hoverScale={true}
              hoverBg={true}
            />
          </div>
        </div>

        <div
          className={`${
            modalType === "uidGuideVideo" ? "visible" : "invisible"
          } transition-all duration-[400ms] ${
            isModalOpen ? "opacity-100	" : "opacity-0"
          } uid-find-guide-video-modal flex flex-col justify-center items-center`}
        ></div>
      </Modal>
    </div>
  );
};

export default RegistrationExistingAccount;

RegistrationExistingAccount.getLayout = function getLayout(page: ReactElement) {
  return <NoLayout>{page}</NoLayout>;
};

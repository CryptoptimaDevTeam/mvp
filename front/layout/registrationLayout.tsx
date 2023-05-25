import { RegistrationHeader } from "../components/blocks/registrationHeader";

export type LayoutProps = {
  children: React.ReactNode;
};

const RegistrationLayout = ({ children }: LayoutProps) => {
  return (
    <>
      <RegistrationHeader />
      <main>{children}</main>
    </>
  );
};

export default RegistrationLayout;

import { Header } from "../components/blocks/header";
import { Footer } from "../components/blocks/footer";

export type LayoutProps = {
  children: React.ReactNode;
};

const DefaultLayout = ({ children }: LayoutProps) => {
  return (
    <>
      <Header />
      <main>{children}</main>
      <Footer />
    </>
  );
};

export default DefaultLayout;

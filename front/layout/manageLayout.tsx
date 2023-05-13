import { ManageHeader } from "../components/blocks/manageHeader";
export type LayoutProps = {
  children: React.ReactNode;
};

const ManageLayout = ({ children }: LayoutProps) => {
  return (
    <>
      <ManageHeader />
      <main>{children}</main>
    </>
  );
};

export default ManageLayout;

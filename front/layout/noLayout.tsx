export type LayoutProps = {
  children: React.ReactNode;
};

const NoLayout = ({ children }: LayoutProps) => {
  return <main className="min-h-screen">{children}</main>;
};

export default NoLayout;

import {Settings as LayoutSettings} from '@ant-design/pro-components';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#13C2C2',
  layout: 'side',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  title: '智能考勤系统',
  pwa: false,
  logo: 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/%E8%80%83%E5%8B%A4%E7%AE%A1%E7%90%86-logo.webp',
  headerHeight: 48,
  splitMenus: false,
};

export default Settings;

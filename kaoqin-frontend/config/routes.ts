export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {name: '登录', path: '/user/login', component: './user/Login'},
      {name: '登录', path: '/user/register', component: './user/Register'},
      {component: './404'},
    ],
  },
  {path: '/welcome', name: '报表中心', access: 'canAdmin', icon: 'bar-chart', component: './Welcome'},
  {
    path: '/admin/personnel', access: 'canAdmin', name: '人事管理', icon: 'contacts',
    routes: [
      {path: '/admin/personnel/department', name: '部门管理', component: './department'},
      {path: '/admin/personnel/employee', name: '员工管理', component: './employee'},
    ]
  },
  {path: '/admin/user', access: 'canAdmin', name: '用户管理', icon: 'user', component: './UserAdmin'},
  {path: '/admin/attendance', access: 'canAdmin', name: '考勤管理', icon: 'file-done', component: './attendance'},
  {path: '/CheckIn', name: '员工考勤', icon: 'contacts', component: './CheckIn'},
  {path: '/MyAttendance', name: '我的考勤', icon: 'calendar', component: './MyAttendance'},
  {path: '/', redirect: '/welcome'},
  {component: './404'},
];

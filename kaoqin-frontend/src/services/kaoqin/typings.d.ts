declare namespace API {
  type Attendance = {
    attendanceDate?: string;
    attendanceType?: string;
    createTime?: string;
    employeeAccount?: string;
    id?: number;
    isDelete?: number;
    name?: string;
    updateTime?: string;
  };

  type AttendanceAddRequest = {
    attendanceDate?: string;
    attendanceType?: string;
    employeeAccount?: string;
    name?: string;
  };

  type AttendanceUpdateRequest = {
    attendanceDate?: string;
    attendanceType?: string;
    employeeAccount?: string;
    id?: number;
    name?: string;
  };

  type BaseResponseAttendance = {
    code?: number;
    data?: Attendance;
    message?: string;
  };

  type BaseResponseboolean = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseDepartment = {
    code?: number;
    data?: Department;
    message?: string;
  };

  type BaseResponseEmployee = {
    code?: number;
    data?: Employee;
    message?: string;
  };

  type BaseResponseListAttendance = {
    code?: number;
    data?: Attendance[];
    message?: string;
  };

  type BaseResponseListDepartment = {
    code?: number;
    data?: Department[];
    message?: string;
  };

  type BaseResponseListEmployeeVO = {
    code?: number;
    data?: EmployeeVO[];
    message?: string;
  };

  type BaseResponseListPost = {
    code?: number;
    data?: Post[];
    message?: string;
  };

  type BaseResponseListUserVO = {
    code?: number;
    data?: UserVO[];
    message?: string;
  };

  type BaseResponselong = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageAttendance = {
    code?: number;
    data?: PageAttendance;
    message?: string;
  };

  type BaseResponsePageDepartment = {
    code?: number;
    data?: PageDepartment;
    message?: string;
  };

  type BaseResponsePageEmployee = {
    code?: number;
    data?: PageEmployee;
    message?: string;
  };

  type BaseResponsePagePost = {
    code?: number;
    data?: PagePost;
    message?: string;
  };

  type BaseResponsePageUserVO = {
    code?: number;
    data?: PageUserVO;
    message?: string;
  };

  type BaseResponsePost = {
    code?: number;
    data?: Post;
    message?: string;
  };

  type BaseResponseUser = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BaseResponseUserVO = {
    code?: number;
    data?: UserVO;
    message?: string;
  };

  type DeleteRequest = {
    id?: number;
  };

  type Department = {
    createTime?: string;
    departmentName?: string;
    id?: number;
    isDelete?: number;
    updateTime?: string;
  };

  type DeptAddRequest = {
    departmentName?: string;
  };

  type DeptUpdateRequest = {
    departmentName?: string;
    id?: number;
  };

  type Employee = {
    createTime?: string;
    department?: string;
    departmentId?: number;
    employeeAccount?: string;
    employeeAvatar?: string;
    employeeName?: string;
    employeePosition?: string;
    gender?: number;
    id?: number;
    isDelete?: number;
    updateTime?: string;
  };

  type EmployeeAddRequest = {
    department?: string;
    employeeAccount?: string;
    employeeAvatar?: string;
    employeeName?: string;
    employeePosition?: string;
    gender?: number;
  };

  type EmployeeUpdateRequest = {
    department?: string;
    employeeAccount?: string;
    employeeAvatar?: string;
    employeeName?: string;
    employeePosition?: string;
    gender?: number;
    id?: number;
  };

  type EmployeeVO = {
    createTime?: string;
    department?: string;
    employeeAccount?: string;
    employeeAvatar?: string;
    employeeName?: string;
    employeePosition?: string;
    gender?: number;
    id?: number;
    updateTime?: string;
  };

  type getAttendanceByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getDepartmentByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getEmployeeByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getMyAttendanceUsingGETParams = {
    attendanceDate?: string;
    attendanceType?: string;
    current?: number;
    employeeAccount?: string;
    name?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type getPostByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type listAttendanceByPageUsingGETParams = {
    attendanceDate?: string;
    attendanceType?: string;
    current?: number;
    employeeAccount?: string;
    name?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type listAttendanceUsingGETParams = {
    attendanceDate?: string;
    attendanceType?: string;
    current?: number;
    employeeAccount?: string;
    name?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type listDepartmentByPageUsingGETParams = {
    current?: number;
    departmentName?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type listDepartmentUsingGETParams = {
    current?: number;
    departmentName?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type listEmployeeByPageUsingGETParams = {
    current?: number;
    department?: string;
    employeeAccount?: string;
    employeeAvatar?: string;
    employeeName?: string;
    employeePosition?: string;
    gender?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type listEmployeeUsingGETParams = {
    current?: number;
    department?: string;
    employeeAccount?: string;
    employeeAvatar?: string;
    employeeName?: string;
    employeePosition?: string;
    gender?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type listPostByPageUsingGETParams = {
    age?: number;
    contact?: string;
    content?: string;
    current?: number;
    education?: string;
    gender?: number;
    job?: string;
    loveExp?: string;
    pageSize?: number;
    place?: string;
    reviewStatus?: number;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type listPostUsingGETParams = {
    age?: number;
    contact?: string;
    content?: string;
    current?: number;
    education?: string;
    gender?: number;
    job?: string;
    loveExp?: string;
    pageSize?: number;
    place?: string;
    reviewStatus?: number;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type listUserByPageUsingGETParams = {
    createTime?: string;
    current?: number;
    employeeAccount?: string;
    gender?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userRole?: string;
  };

  type listUserUsingGETParams = {
    createTime?: string;
    current?: number;
    employeeAccount?: string;
    gender?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userRole?: string;
  };

  type ModelAndView = {
    empty?: boolean;
    model?: Record<string, any>;
    modelMap?: Record<string, any>;
    reference?: boolean;
    status?:
      | 'ACCEPTED'
      | 'ALREADY_REPORTED'
      | 'BAD_GATEWAY'
      | 'BAD_REQUEST'
      | 'BANDWIDTH_LIMIT_EXCEEDED'
      | 'CHECKPOINT'
      | 'CONFLICT'
      | 'CONTINUE'
      | 'CREATED'
      | 'DESTINATION_LOCKED'
      | 'EXPECTATION_FAILED'
      | 'FAILED_DEPENDENCY'
      | 'FORBIDDEN'
      | 'FOUND'
      | 'GATEWAY_TIMEOUT'
      | 'GONE'
      | 'HTTP_VERSION_NOT_SUPPORTED'
      | 'IM_USED'
      | 'INSUFFICIENT_SPACE_ON_RESOURCE'
      | 'INSUFFICIENT_STORAGE'
      | 'INTERNAL_SERVER_ERROR'
      | 'I_AM_A_TEAPOT'
      | 'LENGTH_REQUIRED'
      | 'LOCKED'
      | 'LOOP_DETECTED'
      | 'METHOD_FAILURE'
      | 'METHOD_NOT_ALLOWED'
      | 'MOVED_PERMANENTLY'
      | 'MOVED_TEMPORARILY'
      | 'MULTIPLE_CHOICES'
      | 'MULTI_STATUS'
      | 'NETWORK_AUTHENTICATION_REQUIRED'
      | 'NON_AUTHORITATIVE_INFORMATION'
      | 'NOT_ACCEPTABLE'
      | 'NOT_EXTENDED'
      | 'NOT_FOUND'
      | 'NOT_IMPLEMENTED'
      | 'NOT_MODIFIED'
      | 'NO_CONTENT'
      | 'OK'
      | 'PARTIAL_CONTENT'
      | 'PAYLOAD_TOO_LARGE'
      | 'PAYMENT_REQUIRED'
      | 'PERMANENT_REDIRECT'
      | 'PRECONDITION_FAILED'
      | 'PRECONDITION_REQUIRED'
      | 'PROCESSING'
      | 'PROXY_AUTHENTICATION_REQUIRED'
      | 'REQUESTED_RANGE_NOT_SATISFIABLE'
      | 'REQUEST_ENTITY_TOO_LARGE'
      | 'REQUEST_HEADER_FIELDS_TOO_LARGE'
      | 'REQUEST_TIMEOUT'
      | 'REQUEST_URI_TOO_LONG'
      | 'RESET_CONTENT'
      | 'SEE_OTHER'
      | 'SERVICE_UNAVAILABLE'
      | 'SWITCHING_PROTOCOLS'
      | 'TEMPORARY_REDIRECT'
      | 'TOO_EARLY'
      | 'TOO_MANY_REQUESTS'
      | 'UNAUTHORIZED'
      | 'UNAVAILABLE_FOR_LEGAL_REASONS'
      | 'UNPROCESSABLE_ENTITY'
      | 'UNSUPPORTED_MEDIA_TYPE'
      | 'UPGRADE_REQUIRED'
      | 'URI_TOO_LONG'
      | 'USE_PROXY'
      | 'VARIANT_ALSO_NEGOTIATES';
    view?: View;
    viewName?: string;
  };

  type OrderItem = {
    asc?: boolean;
    column?: string;
  };

  type PageAttendance = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: Attendance[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageDepartment = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: Department[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageEmployee = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: Employee[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PagePost = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: Post[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserVO = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type Post = {
    age?: number;
    contact?: string;
    content?: string;
    createTime?: string;
    education?: string;
    gender?: number;
    id?: number;
    isDelete?: number;
    job?: string;
    loveExp?: string;
    photo?: string;
    place?: string;
    reviewMessage?: string;
    reviewStatus?: number;
    thumbNum?: number;
    updateTime?: string;
    userId?: number;
    viewNum?: number;
  };

  type PostAddRequest = {
    age?: number;
    contact?: string;
    content?: string;
    education?: string;
    gender?: number;
    job?: string;
    loveExp?: string;
    photo?: string;
    place?: string;
  };

  type PostUpdateRequest = {
    age?: number;
    contact?: string;
    content?: string;
    education?: string;
    gender?: number;
    id?: number;
    job?: string;
    loveExp?: string;
    photo?: string;
    place?: string;
    reviewMessage?: string;
    reviewStatus?: number;
  };

  type User = {
    createTime?: string;
    employeeAccount?: string;
    gender?: number;
    id?: number;
    isDelete?: number;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userPassword?: string;
    userRole?: string;
  };

  type UserAddRequest = {
    employeeAccount?: string;
    gender?: number;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userPassword?: string;
    userRole?: string;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserRegisterRequest = {
    checkPassword?: string;
    employeeAccount?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type UserUpdateRequest = {
    gender?: number;
    id?: number;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userPassword?: string;
    userRole?: string;
  };

  type UserVO = {
    createTime?: string;
    employeeAccount?: string;
    gender?: number;
    id?: number;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userRole?: string;
  };

  type View = {
    contentType?: string;
  };
}

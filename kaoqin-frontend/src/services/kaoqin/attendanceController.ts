// @ts-ignore
/* eslint-disable */
import request from '@/plugins/globalRequest';

/** addAttendance POST /api/attendance/add */
export async function addAttendanceUsingPOST(
  body: API.AttendanceAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponselong>('/api/attendance/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteAttendance POST /api/attendance/delete */
export async function deleteAttendanceUsingPOST(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/attendance/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAttendanceById GET /api/attendance/get */
export async function getAttendanceByIdUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAttendanceByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAttendance>('/api/attendance/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getMyAttendance GET /api/attendance/getMyAttendance */
export async function getMyAttendanceUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getMyAttendanceUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListAttendance>('/api/attendance/getMyAttendance', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listAttendance GET /api/attendance/list */
export async function listAttendanceUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listAttendanceUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListAttendance>('/api/attendance/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listAttendanceByPage GET /api/attendance/list/page */
export async function listAttendanceByPageUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listAttendanceByPageUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageAttendance>('/api/attendance/list/page', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateAttendance POST /api/attendance/update */
export async function updateAttendanceUsingPOST(
  body: API.AttendanceUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/attendance/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

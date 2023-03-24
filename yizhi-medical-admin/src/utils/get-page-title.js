import defaultSettings from '@/settings'

const title = defaultSettings.title || '易知挂号'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}

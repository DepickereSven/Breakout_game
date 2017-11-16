exports.User = class User {
  constructor ({ id, username, imageUrl, email, country }) {
    this.id = id
    this.username = username
    this.imageUrl = imageUrl
    this.email = email
    this.country = country
  }
}
